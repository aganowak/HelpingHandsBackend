package com.codecool.helpinghands.repository;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface UserRepository {

    List<User> USERS_IN_MEMORY = new ArrayList<>(
            Arrays.asList(
                    new User(0, "Barabasz", "Barabejski", "Bari", "bari@buziaczek.pl", 
                       "bari123", null, "", false, -1)
                )
            
    );
}
