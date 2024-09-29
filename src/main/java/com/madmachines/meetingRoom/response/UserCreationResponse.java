package com.madmachines.meetingRoom.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserCreationResponse {
    private String uniqueId;
}
