package com.codecool.helpinghands.repository;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.model.User_Event_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRoleRepository extends JpaRepository<User_Event_Role, Integer>  {
    User_Event_Role findByUserAndEvent(User user, Event event);
}



