package com.codecool.helpinghands.controller;


import com.codecool.helpinghands.dto.UserDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.service.EventService;
import com.codecool.helpinghands.service.SlotService;
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
    private final SlotService slotService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, EventService eventService, UserEventRoleService userEventRoleService,SlotService slotService , ModelMapper modelMapper) {

        this.userService = userService;
        this.eventService = eventService;
        this.userEventRoleService = userEventRoleService;
        this.slotService = slotService;
        this.modelMapper = modelMapper;
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

    @PostMapping("/users/register")
    public UserDTO registerUser(
            @RequestParam("userNickname") String userNickname,
            @RequestParam("userEmail") String userEmail,
            @RequestParam("password") String password
    ){
        //move validation
        if (userService.findByUserEmail(userEmail) != null) {
            var u = new UserDTO();
            u.setUserId(-1);
            u.setFirstName("User already exists!");
            return u;
        }
        if (!userEmail.matches("^(.+)@(\\S+)$")) {
            var u = new UserDTO();
            u.setUserId(-1);
            u.setFirstName("Please enter correct email");
            return u;
        }
        /*
        if (!firstName.matches("^\\S+$")) {
            var u = new UserDTO();
            u.setUserId(-1);
            u.setFirstName("Please enter correct first name");
            return u;
        }
        if (!lastName.matches("^\\S+$")) {
            var u = new UserDTO();
            u.setUserId(-1);
            u.setFirstName("Please enter correct last name");
            return u;
        }
         */
        if (!password.matches("^.{8,}$")) {
            var u = new UserDTO();
            u.setUserId(-1);
            u.setFirstName("Password should bee at least 8 characters long");
            return u;
        }
        User user = userService.addUser(userNickname, userEmail, password);
        return convertUserToUserDto(user);
    }

    public UserDTO convertUserToUserDto(User user){
        return modelMapper.map(user, UserDTO.class);
    }

}
