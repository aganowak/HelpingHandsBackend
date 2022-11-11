package com.codecool.helpinghands.service;

import com.codecool.helpinghands.dto.EventDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private SlotRepository slotRepository;

    @Autowired
    public EventService(EventRepository eventRepository, SlotRepository slotRepository) {

        this.eventRepository = eventRepository;
        this.slotRepository = slotRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "dateOfEvent"));
    }


    public Optional<Event> getEventById(int eventId) {
        return eventRepository.findById(eventId);
    }

    public Event addEvent(String city, EventCategory eventCategory, String eventDescription, String eventTitle, String imagePath, int slotNum, String dateOfEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //convert String to LocalDate
        LocalDate dateOfEventFormatted = LocalDate.parse(dateOfEvent, formatter);

        Event event = new Event(eventTitle, eventDescription, eventCategory, city, slotNum, imagePath, dateOfEventFormatted);
        return eventRepository.save(event);
    }

    public Event addSlotToEvent(int eventId, int slotStartHour, int slotStartMinutes, int slotEndHour, int slotEndMinutes){
        Event event = eventRepository.findById(eventId).get();
        LocalDate dateOfEvent = event.getDateOfEvent();
        LocalDateTime slotStartLDT = dateOfEvent.atTime(slotStartHour, slotStartMinutes);
        LocalDateTime slotEndLDT = dateOfEvent.atTime(slotEndHour, slotEndMinutes);
        Slot slot = new Slot (event, slotStartLDT, slotEndLDT);
        slotRepository.save(slot);
        return event;
    }

}
