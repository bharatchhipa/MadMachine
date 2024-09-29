package com.madmachines.meetingRoom.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class FetchUserResponse {
    private String name;
    private String email;
    private String uniqueId;
}
