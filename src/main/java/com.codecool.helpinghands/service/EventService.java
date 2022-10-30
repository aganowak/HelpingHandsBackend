package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    public List<Event> getEvents(){
        return EventRepository.EVENTS_IN_MEMORY;
    }

}
