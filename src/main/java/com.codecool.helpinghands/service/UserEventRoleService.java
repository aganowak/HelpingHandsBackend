package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.model.UserRole;
import com.codecool.helpinghands.model.User_Event_Role;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.UserEventRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventRoleService {

    private UserEventRoleRepository userEventRoleRepository;

    @Autowired
    public UserEventRoleService(UserEventRoleRepository userEventRoleRepository) {
        this.userEventRoleRepository = userEventRoleRepository;
    }


    public void assignVolunteerToEvent(User user, Event event) {
        userEventRoleRepository.save(new User_Event_Role(user, event, UserRole.VOLUNTEER));
    }

    public void removeVolunteerFromEvent(User user, Event event){
        User_Event_Role assignment = userEventRoleRepository.findByUserAndEvent(user, event);
        userEventRoleRepository.delete(assignment);
    }
}
