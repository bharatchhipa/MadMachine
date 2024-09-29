package com.madmachines.meetingRoom.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FetchUserRequest {
    @NotEmpty
    private String email;
}
