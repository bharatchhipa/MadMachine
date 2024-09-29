package com.madmachines.meetingRoom.repository;

import com.madmachines.meetingRoom.entity.ApiReqResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiReqRespRepository extends JpaRepository<ApiReqResp, Long> {
}
