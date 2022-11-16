package com.codecool.helpinghands.controller;


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
        return userService.assignUserToSlotAndEvent(loggedInUser, slotId);

    }
    @DeleteMapping("/users/assign/{slotId}")
    public User deleteAssignedUserFromSlotAndEvent(@PathVariable("slotId") int slotId){
        User loggedInUser = userService.getUserById(1);
        return userService.deleteUserFromSlotAndEvent(slotId, loggedInUser);
    }

}
