package com.madmachines.meetingRoom.repository;


import com.madmachines.meetingRoom.entity.MeetingRoom;
import com.madmachines.meetingRoom.request.MeetingRoomRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    MeetingRoom findByName(@NotEmpty String roomName);

    MeetingRoom findByNameOrUniqueId(String roomName, String roomId);

    List<MeetingRoom> findAllByOwnerId(@NotEmpty String ownerId);
}

