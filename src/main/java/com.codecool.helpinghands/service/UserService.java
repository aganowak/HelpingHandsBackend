package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private EventRepository eventRepository;
    @Autowired
    public UserService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

//        public List<User> getUsers(int userId, int eventId){
//
//            Optional<Event> event = eventRepository.findById(eventId);
//            Optional<Event> user = eventRepository.findById(userId);
//            if(event.isPresent() && user.isPresent()){
//                userRepository.save()
//            }
//            return UserRepository.USERS_IN_MEMORY;
//    }


}
