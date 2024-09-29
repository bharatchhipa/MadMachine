package com.madmachines.meetingRoom.repository;


import com.madmachines.meetingRoom.entity.Meeting;
import com.madmachines.meetingRoom.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByRoomUniqueIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(String roomId, Date endTime, Date startTime);

    List<Meeting> findByParticipantsAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(String participantEmail, Date endTime, Date startTime);

    @Query("SELECT m FROM Meeting m WHERE :email MEMBER OF m.participants AND " +
            "(:startTime < m.endTime AND :endTime > m.startTime)")
    List<Meeting> findConflictingMeetingsByParticipantEmailAndTime(@Param("email") String email,
                                                                   @Param("startTime") Date startTime,
                                                                   @Param("endTime") Date endTime);

}

