package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private EventRepository eventRepository;
    private SlotService slotService;
    @Autowired
    public UserService(UserRepository userRepository, EventRepository eventRepository, SlotService slotService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.slotService = slotService;
    }

    public User getUserById(int userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserSlot(User user){

        return userRepository.save(user);
    }

    public User deleteUserFromSlot(int slotId, User loggedInUser) {

        Slot slotToRemove = slotService.getSlotById(slotId);
        System.out.println("slot to delete : ");
        System.out.println(slotToRemove);
        loggedInUser.getUserSlots().remove(slotToRemove);
        System.out.println("logger user after delete slot : ");
        userRepository.save(loggedInUser);

        return  loggedInUser;
    }
}
