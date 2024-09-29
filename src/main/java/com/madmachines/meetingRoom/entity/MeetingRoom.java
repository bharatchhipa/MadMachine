package com.madmachines.meetingRoom.entity;


import com.madmachines.meetingRoom.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MeetingRoom extends BaseEntity {
    private String name;
    private int capacity;
    private String uniqueId;
    private String ownerId;
}

