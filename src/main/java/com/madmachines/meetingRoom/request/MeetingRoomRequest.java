package com.madmachines.meetingRoom.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MeetingRoomRequest {
    @NotEmpty
    private String roomName;
    @Max(99)
    @Min(2)
    @NotNull
    private Integer capacity;
    @NotEmpty
    private String ownerId;

}
