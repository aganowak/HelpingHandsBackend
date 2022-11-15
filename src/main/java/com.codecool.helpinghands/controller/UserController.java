package com.codecool.helpinghands.controller;


import com.codecool.helpinghands.dto.EventDTO;
import com.codecool.helpinghands.dto.UserDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.service.EventService;
import com.codecool.helpinghands.service.UserEventRoleService;
import com.codecool.helpinghands.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final EventService eventService;
    private final UserEventRoleService userEventRoleService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, EventService eventService, UserEventRoleService userEventRoleService, ModelMapper modelMapper) {

        this.userService = userService;
        this.eventService = eventService;
        this.userEventRoleService = userEventRoleService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/users/assign/{eventId}")
    public void assignUserToEvent(@PathVariable("eventId") int eventId){
        User loggedInUser = userService.getUserById(1);
        Event event = eventService.getEventById(eventId);
        userEventRoleService.assignVolunteerToEvent(loggedInUser, event);
    }

    @DeleteMapping("/users/assign/{eventId}")
    public void removeUserFromEvent(@PathVariable("eventId") int eventId){
        User loggedInUser = userService.getUserById(1);
        Event event = eventService.getEventById(eventId);
        userEventRoleService.removeVolunteerFromEvent(loggedInUser, event);
    }
    @PostMapping("/users")
    public UserDTO registerUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("userNickname") String userNickname,
            @RequestParam("userEmail") String userEmail,
            @RequestParam("password") String password,
            @RequestParam("userImagePath") String userImagePath
    ){
        User user = userService.addUser(firstName, lastName, userNickname, userEmail, password, userImagePath);
        return convertUserToUserDto(user);
    }

    public UserDTO convertUserToUserDto(User user){
        return modelMapper.map(user, UserDTO.class);
    }

}
