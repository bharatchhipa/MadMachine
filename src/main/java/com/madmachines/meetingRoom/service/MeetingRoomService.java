package com.madmachines.meetingRoom.service;

import com.madmachines.meetingRoom.entity.Meeting;
import com.madmachines.meetingRoom.entity.MeetingRoom;
import com.madmachines.meetingRoom.repository.MeetingRoomRepository;
import com.madmachines.meetingRoom.request.MeetingRoomRequest;
import com.madmachines.meetingRoom.request.RoomListRequest;
import com.madmachines.meetingRoom.response.MeetingRoomResponse;
import com.madmachines.meetingRoom.response.ResponseWrapper;
import com.madmachines.meetingRoom.response.RoomListResponse;
import com.madmachines.meetingRoom.response.RoomResponse;
import com.madmachines.meetingRoom.wrapper.ResponseEntityWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class MeetingRoomService {
    private final MeetingRoomRepository meetingRoomRepository;



    public ResponseEntity<ResponseWrapper> createRoom(MeetingRoomRequest meetingRoomRequest) {
        if(meetingRoomRepository.findByName(meetingRoomRequest.getRoomName()) != null){
            log.info("A room already exists with same name");
            return ResponseEntityWrapper.badRequestException("A room already exists with same name");
        }
        String uniqueId = UUID.randomUUID().toString();
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setName(meetingRoomRequest.getRoomName());
        meetingRoom.setUniqueId(uniqueId);
        meetingRoom.setOwnerId(meetingRoomRequest.getOwnerId());
        meetingRoom.setCapacity(meetingRoomRequest.getCapacity());
        meetingRoom = meetingRoomRepository.save(meetingRoom);

        MeetingRoomResponse meetingRoomResponse = MeetingRoomResponse.builder().
        roomId(uniqueId).build();

        return ResponseEntityWrapper.successResponseBuilder("Room created successfully",meetingRoomResponse);
    }


    public ResponseEntity<ResponseWrapper> getRoomList(RoomListRequest roomListRequest) {

        List<MeetingRoom> meetingRoomList =  meetingRoomRepository.findAllByOwnerId(roomListRequest.getOwnerId());

        RoomListResponse roomListResponse = new RoomListResponse();

        List<RoomResponse> rooms = meetingRoomList.stream().map(meetingRoom -> {
            return RoomResponse.builder().roomId(meetingRoom.getUniqueId()).roomName(meetingRoom.getName()).capacity(meetingRoom.getCapacity()).build();
        }).collect(Collectors.toList());
        roomListResponse.setRooms(rooms);

        return ResponseEntityWrapper.successResponseBuilder("Room created successfully",roomListResponse);
    }
}
