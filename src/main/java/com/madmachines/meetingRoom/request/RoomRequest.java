package com.madmachines.meetingRoom.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RoomRequest {
    @NotEmpty
    private String roomId;
    @NotEmpty
    private String roomName;
}
