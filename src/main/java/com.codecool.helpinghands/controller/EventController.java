package com.codecool.helpinghands.controller;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

    @GetMapping("/event/{eventId}")
    public Optional<Event> getEventById(
            @PathVariable("eventId") int eventId
    ){

        return eventService.getEventById(eventId);
    }

    @PostMapping("/event")
    public Event addEvent(
            @RequestParam("city") String city,
            @RequestParam("eventCategory") EventCategory eventCategory,
            @RequestParam("eventDescription") String eventDescription,
            @RequestParam("eventTitle") String eventTitle,
            @RequestParam("imagePath") String imagePath,
            @RequestParam("slotNum") int slotNum,
            @RequestParam("dateOfEvent") String dateOfEvent
    ){

        return eventService.addEvent(city, eventCategory, eventDescription, eventTitle, imagePath, slotNum, dateOfEvent);
    }

    @GetMapping(path = "/")
    public String mainPage(){
        return "This is Helping Hands app";
    }

}
