package com.madmachines.meetingRoom.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomListResponse {
    List<RoomResponse> rooms;
}
