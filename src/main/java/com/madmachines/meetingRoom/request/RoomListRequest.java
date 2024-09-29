package com.madmachines.meetingRoom.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Valid
public class RoomListRequest {
    @NotEmpty
    private String ownerId;
}
