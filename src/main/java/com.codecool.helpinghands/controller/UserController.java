package com.codecool.helpinghands.controller;


import com.codecool.helpinghands.dto.RegistrationDTO;
import com.codecool.helpinghands.dto.UserDTO;
import com.codecool.helpinghands.dto.EventDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.service.EventService;
import com.codecool.helpinghands.service.SlotService;
import com.codecool.helpinghands.service.UserEventRoleService;
import com.codecool.helpinghands.service.UserService;
import com.codecool.helpinghands.validator.WrongInputException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/assign/{slotId}")
    public User assignUserToEventAndSlot(@PathVariable("slotId") int slotId){
        User loggedInUser = userService.getUserById(1);
        return userService.assignUserToSlotAndEvent(loggedInUser, slotId);
    }

    @DeleteMapping("/users/assign/{slotId}")
    public User deleteAssignedUserFromSlotAndEvent(@PathVariable("slotId") int slotId){
        User loggedInUser = userService.getUserById(1);
        return userService.deleteUserFromSlotAndEvent(slotId, loggedInUser);
    }

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        user.setDateJoined(LocalDateTime.now());
        try {
            userService.verifyUserInput(user);
            userService.addUser(user);
        } catch (WrongInputException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("New user registered", HttpStatus.OK);
    }

}
