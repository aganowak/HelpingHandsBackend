package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.UserEventRoleRepository;
import com.codecool.helpinghands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
         // assign user to event
        Event event = slotService.getEventBySlotId(slotId);
        userEventRoleService.assignVolunteerToEvent(user, event);
        // assign user to slot
        Slot slot = slotService.getSlotById(slotId);
        user.addSlot(slot);
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
