package com.codecool.helpinghands.controller;


import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.service.EventService;
import com.codecool.helpinghands.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private UserService userService;
    private EventService eventService;

    @Autowired
    public UserController(UserService userService, EventService eventService) {

        this.userService = userService;
        this.eventService = eventService;
    }

//    @GetMapping("/users")
//    public List<User> getUsers(){
//        return userService.getUsers();
//    }
//
//    // curl -d "eventId=1444" -X POST -H "Content-Type: application/x-www-form-urlencoded" http://localhost:8080/user/0/assignEvent
//    @PostMapping("/user/{userId}/assignEvent")
//    public String assignEvent(@PathVariable("userId") int userId, @RequestParam int eventId){
//
//        // do the assignment
//        User selected = null;
//        for (var user : userService.getUsers()) {
//            if (user.getUserId() == userId)
//            {
//                selected = user;
//                break;
//            }
//        }
//        if (selected == null)
//            return "Error: User not found!";
//        selected.assign(eventId);
//        return "User %d assigned to event %d".formatted(selected.getUserId(), eventId);
//    }


    @PostMapping("/users/assign/{eventId}")
    public void assignUserToEvent(@PathVariable("eventId") int eventId){
        User loggedInUser = userService.getUserById(1).get();
        Event event = eventService.getEventById(eventId).get();
        loggedInUser.assign(event);
        System.out.println(loggedInUser);
    }
}
