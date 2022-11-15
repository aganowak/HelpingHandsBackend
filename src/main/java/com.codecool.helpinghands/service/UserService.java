package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private EventRepository eventRepository;
    @Autowired
    public UserService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public User getUserById(int userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserSlot(User user){

        return userRepository.save(user);
    }

    public User addUser(String firstName, String lastName, String userNickname, String userEmail, String password, String userImagePath) {
        User user = new User(firstName, lastName, userNickname, userEmail, password, userImagePath);
        return userRepository.save(user);
    }
}
