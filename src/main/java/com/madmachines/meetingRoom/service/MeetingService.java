package com.madmachines.meetingRoom.service;

import com.madmachines.meetingRoom.constants.ResponseConstants;
import com.madmachines.meetingRoom.entity.Meeting;
import com.madmachines.meetingRoom.entity.MeetingRoom;
import com.madmachines.meetingRoom.entity.User;
import com.madmachines.meetingRoom.exceptions.BadRequestException;
import com.madmachines.meetingRoom.repository.MeetingRepository;
import com.madmachines.meetingRoom.repository.MeetingRoomRepository;
import com.madmachines.meetingRoom.repository.UserRepository;
import com.madmachines.meetingRoom.request.MeetingRequest;
import com.madmachines.meetingRoom.response.ResponseWrapper;
import com.madmachines.meetingRoom.wrapper.ResponseEntityWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeetingService {


    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    public ResponseWrapper isRoomAvailable(String roomId, String userId, List<String> participantEmails, Date startTime, Date endTime,String name) {

        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStatus(ResponseConstants.FAILURE);

        User user = userRepository.findByUniqueId(userId);
        if(user == null ){
            log.info("User not present");
            throw new BadRequestException("User Id not present in the system");
        }

        MeetingRoom meetingRoom = meetingRoomRepository.findByNameOrUniqueId(name,roomId);
        if(meetingRoom == null){
            log.info("no room found with given name or room id");
            throw new BadRequestException("No room found with the given name or roomId");
        }
        if(!meetingRoom.getOwnerId().equalsIgnoreCase(userId)) {
            log.info("You are not the owner of the room");
            responseWrapper.setMessage("You are not the owner of this room");
            return responseWrapper;
        }

        // 1. Check if the room is available
        // only one meeting can be scheduled in a give time frame for a room
        List<Meeting> conflictingMeetings = meetingRepository.findByRoomUniqueIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(roomId, endTime, startTime);
        if (!conflictingMeetings.isEmpty()) {
            log.info("There is already a meeting scheduled at the given time frame");
            responseWrapper.setMessage("There is already a meeting scheduled at the given time frame");
            return responseWrapper;
        }


        // 2. Check if the meeting creator has any conflicting meetings
        List<Meeting> creatorConflicts = meetingRepository.findConflictingMeetingsByParticipantEmailAndTime(user.getEmail(), endTime, startTime);

        if (!creatorConflicts.isEmpty()) {
            log.info("There is already a meeting of you scheduled.");
            responseWrapper.setMessage("There is already a meeting of you scheduled.");
            return responseWrapper;

        }

        // 3. Check if any participants have conflicting meetings
        for (String participantEmail : participantEmails) {
            List<Meeting> participantConflicts = meetingRepository.findByParticipantsAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(participantEmail, endTime, startTime);

            if (!participantConflicts.isEmpty()) {
                log.info("One of the participants have a meeting scheduled");
                responseWrapper.setMessage("One of the participants have a meeting scheduled");
                return responseWrapper;
            }
        }

        // All checks passed, room and participants are available
        responseWrapper.setStatus(ResponseConstants.SUCCESS);
        return responseWrapper;
    }


    public boolean areParticipantsAvailable(List<String> participantEmails, Date startTime, Date endTime) {
        // Check each participant's availability
        for (String email : participantEmails) {
            if (!isUserAvailable(email, startTime, endTime)) {
                return false;
            }
        }
        return true;
    }

    public boolean isUserAvailable(String email, Date startTime, Date endTime) {
        // Here you can implement logic to check the user's availability
        // For this example, let's assume we have a way to check availability
        return true; // Placeholder
    }

    public ResponseEntity<ResponseWrapper> scheduleMeeting(MeetingRequest meeting) {

        ResponseWrapper responseWrapper = isRoomAvailable(meeting.getRoom().getRoomId(),meeting.getUserId(),meeting.getParticipants(), meeting.getStartTime(), meeting.getEndTime(), meeting.getRoom().getRoomName());
        if (!responseWrapper.getStatus().equalsIgnoreCase(ResponseConstants.SUCCESS)) {
            return ResponseEntity.badRequest().body(responseWrapper);
        }

        MeetingRoom meetingRoom = meetingRoomRepository.findByNameOrUniqueId(meeting.getRoom().getRoomName(),meeting.getRoom().getRoomId());
        if(meetingRoom == null){
            throw new BadRequestException("No room found with the given name or roomId");
        }
        Meeting saveMeeting = new Meeting();
        saveMeeting.setRoom(meetingRoom);
        saveMeeting.setStartTime(meeting.getStartTime());
        saveMeeting.setEndTime(meeting.getEndTime());
        saveMeeting.setParticipants(meeting.getParticipants());
        saveMeeting.setTitle(meeting.getTitle());
        meetingRepository.save(saveMeeting);
        return ResponseEntityWrapper.successResponseBuilder("Meeting scheduled");

    }
}
