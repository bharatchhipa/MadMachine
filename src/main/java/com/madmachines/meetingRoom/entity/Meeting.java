package com.madmachines.meetingRoom.entity;

import com.madmachines.meetingRoom.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Meeting extends BaseEntity {
    private String title;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private MeetingRoom room;

    @ElementCollection
    private List<String> participants;

}
