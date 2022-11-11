package com.codecool.helpinghands.service;

import com.codecool.helpinghands.dto.EventWithSlotsDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.model.Slot;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final SlotRepository slotRepository;

    @Autowired
    public EventService(EventRepository eventRepository, SlotRepository slotRepository) {

        this.eventRepository = eventRepository;
        this.slotRepository = slotRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "dateOfEvent"));
    }


    public Event getEventById(int eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        return eventOptional.orElse(null);
    }


    public Event addEvent(String city, EventCategory eventCategory, String eventDescription, String eventTitle, String imagePath, int slotNum, String dateOfEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //convert String to LocalDate
        LocalDate dateOfEventFormatted = LocalDate.parse(dateOfEvent, formatter);

        Event event = new Event(eventTitle, eventDescription, eventCategory, city, slotNum, imagePath, dateOfEventFormatted);
        return eventRepository.save(event);
    }

    public Event addSlotToEvent(int eventId, int slotStartHour, int slotStartMinutes, int slotEndHour, int slotEndMinutes){
        Event event = getEventById(eventId);
        LocalDate dateOfEvent = event.getDateOfEvent();
        if (slotStartHour>0 && slotStartHour<25 && slotStartMinutes>=0 && slotStartMinutes<60 && slotEndHour>0 && slotEndHour<25 && slotEndMinutes>=0 && slotEndMinutes<60){
            LocalDateTime slotStartLDT = dateOfEvent.atTime(slotStartHour, slotStartMinutes);
            LocalDateTime slotEndLDT = dateOfEvent.atTime(slotEndHour, slotEndMinutes);
            Slot slot = new Slot (event, slotStartLDT, slotEndLDT);
            slotRepository.save(slot);
            return event;
        }
        return null;
    }


    public void addSlotsToList(EventWithSlotsDTO eventWithSlotsDto) {
        int eventId = eventWithSlotsDto.getEventId();
        Event event = getEventById(eventId);
        Set<Slot> slotsForEvent = slotRepository.findSlotsByEvent(event);
        eventWithSlotsDto.setEventSlots(slotsForEvent);
    }
}
