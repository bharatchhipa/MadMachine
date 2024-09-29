package com.madmachines.meetingRoom.entity;


import com.madmachines.meetingRoom.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String name;
    private String email;
    private String uniqueId;

    // Getters and Setters
}

