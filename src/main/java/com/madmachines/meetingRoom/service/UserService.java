package com.madmachines.meetingRoom.service;

import com.madmachines.meetingRoom.entity.User;
import com.madmachines.meetingRoom.exceptions.BadRequestException;
import com.madmachines.meetingRoom.repository.UserRepository;
import com.madmachines.meetingRoom.request.FetchUserRequest;
import com.madmachines.meetingRoom.request.UserCreationRequest;
import com.madmachines.meetingRoom.response.FetchUserResponse;
import com.madmachines.meetingRoom.response.ResponseWrapper;
import com.madmachines.meetingRoom.response.UserCreationResponse;
import com.madmachines.meetingRoom.wrapper.ResponseEntityWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<ResponseWrapper> saveUser(UserCreationRequest userCreationRequest) {
        // Check if a user with the same email already exists
        if (userRepository.findByEmail(userCreationRequest.getEmail()) != null) {
            log.info("An email already exists for the given user");
            return ResponseEntityWrapper.badRequestException("A user with this email already exists.");
        }
        String uniqueId = UUID.randomUUID().toString();
        User user = User.builder()
                .name(userCreationRequest.getName())
                .email(userCreationRequest.getEmail())
                .uniqueId(uniqueId).build();
        userRepository.save(user);
        UserCreationResponse userCreationResponse = UserCreationResponse.builder()
                .uniqueId(uniqueId).build();
        log.info("User created");
        return ResponseEntityWrapper.successResponseBuilder("User created successfully",userCreationResponse);
    }


    public ResponseEntity<ResponseWrapper> fetchUser(FetchUserRequest fetchUserRequest) {
        User user = userRepository.findByEmail(fetchUserRequest.getEmail());
        if (user == null) {
            throw new BadRequestException("Email not found");
        }
        FetchUserResponse fetchUserResponse = FetchUserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .uniqueId(user.getUniqueId()).build();

        return ResponseEntityWrapper.successResponseBuilder("User fetched successfully",fetchUserResponse);
    }
}
