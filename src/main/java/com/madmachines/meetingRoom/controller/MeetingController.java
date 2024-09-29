package com.madmachines.meetingRoom.controller;

import com.madmachines.meetingRoom.entity.Meeting;
import com.madmachines.meetingRoom.request.MeetingRequest;
import com.madmachines.meetingRoom.response.ResponseWrapper;
import com.madmachines.meetingRoom.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/schedule")
    public ResponseEntity<ResponseWrapper> scheduleMeeting(@RequestBody @Valid MeetingRequest meetingRequest) {


        return meetingService.scheduleMeeting(meetingRequest);

    }
}
