package com.madmachines.meetingRoom.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomResponse {
    private String roomName;
    private String roomId;
    private Integer capacity;
}
