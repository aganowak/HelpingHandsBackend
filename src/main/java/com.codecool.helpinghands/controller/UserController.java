package com.codecool.helpinghands.controller;


import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.service.EventService;
import com.codecool.helpinghands.service.UserEventRoleService;
import com.codecool.helpinghands.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private UserService userService;
    private EventService eventService;
    private UserEventRoleService userEventRoleService;

    @Autowired
    public UserController(UserService userService, EventService eventService, UserEventRoleService userEventRoleService) {

        this.userService = userService;
        this.eventService = eventService;
        this.userEventRoleService = userEventRoleService;
    }

    @PostMapping("/users/assign/{eventId}")
    public void assignUserToEvent(@PathVariable("eventId") int eventId){
        User loggedInUser = userService.getUserById(1).get();
        Event event = eventService.getEventById(eventId).get();
        userEventRoleService.assignVolunteerToEvent(loggedInUser, event);
    }

    @DeleteMapping("/users/assign/{eventId}")
    public void removeUserFromEvent(@PathVariable("eventId") int eventId){
        User loggedInUser = userService.getUserById(1).get();
        Event event = eventService.getEventById(eventId).get();
        userEventRoleService.removeVolunteerFromEvent(loggedInUser, event);
    }
}
