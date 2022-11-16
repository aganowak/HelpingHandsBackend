package com.codecool.helpinghands.controller;


import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.service.EventService;
import com.codecool.helpinghands.service.SlotService;
import com.codecool.helpinghands.service.UserEventRoleService;
import com.codecool.helpinghands.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final EventService eventService;
    private final UserEventRoleService userEventRoleService;
    private final SlotService slotService;

    @Autowired
    public UserController(UserService userService, EventService eventService, UserEventRoleService userEventRoleService, SlotService slotService) {

        this.userService = userService;
        this.eventService = eventService;
        this.userEventRoleService = userEventRoleService;
        this.slotService = slotService;
    }

    @PostMapping("/users/assign/{slotId}") //slotId
    public User assignUserToEventAndSlot(@PathVariable("slotId") int slotId){
        User loggedInUser = userService.getUserById(1);
        // assign user to event
        Event event = slotService.getEventBySlotId(slotId);
        userEventRoleService.assignVolunteerToEvent(loggedInUser, event);
        // assign user to slot
        Slot slot = slotService.getSlotById(slotId);
        loggedInUser.addSlot(slot);

        return userService.updateUserSlot(loggedInUser);

    }
    @DeleteMapping("/users/assign/{slotId}")
    public User deleteAssignedUserFromSlotAndEvent(@PathVariable("slotId") int slotId){
        User loggedInUser = userService.getUserById(1);
        return userService.deleteUserFromSlotAndEvent(slotId, loggedInUser);
    }

//    @DeleteMapping("/users/assign/{eventId}")
//    public void removeUserFromEvent(@PathVariable("eventId") int eventId){
//        User loggedInUser = userService.getUserById(1);
//        Event event = eventService.getEventById(eventId);
//        userEventRoleService.removeVolunteerFromEvent(loggedInUser, event);
//    }
}
