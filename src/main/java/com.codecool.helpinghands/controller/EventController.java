package com.codecool.helpinghands.controller;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/events")
//    public List<Event> getEvents(){
//        return eventService.getEvents();
//    }
//
//    @GetMapping("/event/{eventId}")
//    public Optional<Event> getEventById(
//            @PathVariable("eventId") int eventId
//    ){
//
//        return eventService.getEventById(eventId);
//    }
//
//    @GetMapping(path = "/")
//    public String mainPage(){
//        return "This is Helping Hands app";
//    }
}
