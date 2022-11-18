package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.UserEventRoleRepository;
import com.codecool.helpinghands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private EventRepository eventRepository;
    private SlotService slotService;
    private final UserEventRoleService userEventRoleService;
    private final UserEventRoleRepository userEventRoleRepository;
    @Autowired
    public UserService(UserRepository userRepository, UserEventRoleRepository userEventRoleRepository,  EventRepository eventRepository, SlotService slotService, UserEventRoleService userEventRoleService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.slotService = slotService;
        this.userEventRoleService = userEventRoleService;
        this.userEventRoleRepository = userEventRoleRepository;
    }

    public User getUserById(int userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User assignUserToSlotAndEvent(User user, int slotId){
        Event event = slotService.getEventBySlotId(slotId);
        Slot slot = slotService.getSlotById(slotId);
        // assign user to slot
        if(isUserAssignToEvent(event.getEventId())){
            user.addSlot(slot);
            userRepository.save(user);
        }
        // assign user to event
        if(isUserAssignToEvent(event.getEventId())) {
            userEventRoleService.assignVolunteerToEvent(user, event);
        }
        return user;
    }


    public boolean isUserAssignToEvent(int eventId){
        Optional<Integer> assignedUserId = userEventRoleRepository.getUserIDFromEvent(eventId);
        if(assignedUserId.isEmpty()){
            return true;
        } else return false;
    }

    public User findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    public User addUser(String userNickname, String userEmail, String password) {
        User user = new User(userNickname, userEmail, password);
        return userRepository.save(user);
    }

    public User deleteUserFromSlotAndEvent(int slotId, User loggedInUser) {
        // remove slot from user slot set
        Slot slotToRemove = slotService.getSlotById(slotId);
        loggedInUser.getUserSlots().remove(slotToRemove);
        userRepository.save(loggedInUser);
        // remove assigned event from user
        Event event = slotService.getEventBySlotId(slotId);
        userEventRoleRepository.deleteUserEventRoleByEventId(event.getEventId(), loggedInUser.getUserId());

        return  loggedInUser;
    }
}
