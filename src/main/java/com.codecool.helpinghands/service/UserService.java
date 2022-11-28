package com.codecool.helpinghands.service;

import com.codecool.helpinghands.dto.RegistrationDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.UserEventRoleRepository;
import com.codecool.helpinghands.repository.UserRepository;
import com.codecool.helpinghands.validator.WrongInputException;
import com.codecool.helpinghands.validator.registrationValidators.RegistrationValidatorFacade;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SlotService slotService;
    private final UserEventRoleService userEventRoleService;
    private final UserEventRoleRepository userEventRoleRepository;
    private final RegistrationValidatorFacade validatorFacade;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;


    public User getUserById(int userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User assignUserToSlotAndEvent(User user, int slotId){
        Event event = slotService.getEventBySlotId(slotId);
        Slot slot = slotService.getSlotById(slotId);
        if(isUserAssignToEvent(event.getEventId())){
            user.addSlot(slot);
            userRepository.save(user);
        }
        if(isUserAssignToEvent(event.getEventId())) {
            userEventRoleService.assignVolunteerToEvent(user, event);
        }
        return user;
    }


    public boolean isUserAssignToEvent(int eventId){
        Optional<Integer> assignedUserId = userEventRoleRepository.getUserIDFromEvent(eventId);
        return assignedUserId.isEmpty();
    }

    public User findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail).orElse(null);
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public User deleteUserFromSlotAndEvent(int slotId, User loggedInUser) {
        Slot slotToRemove = slotService.getSlotById(slotId);
        loggedInUser.getUserSlots().remove(slotToRemove);
        userRepository.save(loggedInUser);
        Event event = slotService.getEventBySlotId(slotId);
        userEventRoleRepository.deleteUserEventRoleByEventId(event.getEventId(), loggedInUser.getUserId());
        return  loggedInUser;
    }

    public void verifyUserInput(User user) throws WrongInputException {
        RegistrationDTO userData = modelMapper.map(user, RegistrationDTO.class);
        validatorFacade.validate(userData);
    }
}
