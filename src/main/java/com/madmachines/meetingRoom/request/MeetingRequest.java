package com.madmachines.meetingRoom.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MeetingRequest {
    @NotEmpty
    private String title;
    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;
    @Valid
    private RoomRequest room;
    @NotEmpty
    private String userId;
    @NotNull
    private List<String> participants;

}
