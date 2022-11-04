package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository eventRepository;
    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(int eventId){
        return eventRepository.findById(eventId);
    }

    public Event addEvent(String city, EventCategory eventCategory, String eventDescription, String eventTitle, String imagePath, int slotNum, String dateOfEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //convert String to LocalDate
        LocalDate dateOfEventFormatted = LocalDate.parse(dateOfEvent, formatter);

        Event event = new Event(eventTitle, eventDescription, eventCategory, city, slotNum, imagePath, dateOfEventFormatted);
        return eventRepository.save(event);
    }

}
