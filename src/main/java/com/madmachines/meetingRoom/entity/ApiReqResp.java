package com.madmachines.meetingRoom.entity;

import com.madmachines.meetingRoom.Utils.AttributeEncryptor;
import com.madmachines.meetingRoom.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
public class ApiReqResp extends BaseEntity {
    private String apiUrl;
    @Lob
    @Convert(converter = AttributeEncryptor.class)
    private String request;
    @Lob
    @Convert(converter = AttributeEncryptor.class)
    private String response;
    private String responseStatus;
    private Integer responseCode;
    private Integer responseEntityCode;
    private String apiMethod;
    private Long reqCompleteTime;
    @Lob
    private String ipAddress;
    private String uniqueId;
    private String uuid;
}