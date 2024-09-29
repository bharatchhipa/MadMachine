package com.madmachines.meetingRoom.controller;

import com.madmachines.meetingRoom.entity.MeetingRoom;
import com.madmachines.meetingRoom.repository.MeetingRoomRepository;
import com.madmachines.meetingRoom.request.MeetingRoomRequest;
import com.madmachines.meetingRoom.request.RoomListRequest;
import com.madmachines.meetingRoom.response.ResponseWrapper;
import com.madmachines.meetingRoom.service.MeetingRoomService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class MeetingRoomController {


    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRoomService meetingRoomService;



    // Endpoint to create a meeting room
    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createRoom(@RequestBody @Valid MeetingRoomRequest meetingRoomRequest) {
        return meetingRoomService.createRoom(meetingRoomRequest);
    }

    @PostMapping("/getAll")
    public ResponseEntity<ResponseWrapper> getAllRooms(@RequestBody @Valid RoomListRequest meetingRoomRequest) {
        return meetingRoomService.getRoomList(meetingRoomRequest);

    }
}
