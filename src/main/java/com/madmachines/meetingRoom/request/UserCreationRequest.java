package com.madmachines.meetingRoom.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserCreationRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
}
