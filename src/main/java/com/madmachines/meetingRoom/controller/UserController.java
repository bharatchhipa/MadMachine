package com.madmachines.meetingRoom.controller;


import com.madmachines.meetingRoom.entity.User;
import com.madmachines.meetingRoom.repository.UserRepository;
import com.madmachines.meetingRoom.request.FetchUserRequest;
import com.madmachines.meetingRoom.request.UserCreationRequest;
import com.madmachines.meetingRoom.response.ResponseWrapper;
import com.madmachines.meetingRoom.service.UserService;
import com.madmachines.meetingRoom.wrapper.ResponseEntityWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper> registerUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {

        return userService.saveUser(userCreationRequest);

    }


    @PostMapping("/fetch")
    public ResponseEntity<ResponseWrapper> fetchUser(@RequestBody @Valid FetchUserRequest fetchUserRequest){
      return userService.fetchUser(fetchUserRequest);
    }
}

