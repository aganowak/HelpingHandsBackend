package com.codecool.helpinghands.repository;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.model.UserEventRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRoleRepository extends JpaRepository<UserEventRole, Integer>  {
    UserEventRole findByUserAndEvent(User user, Event event);
}



