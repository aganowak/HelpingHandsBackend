package com.codecool.helpinghands.controller;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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



    @GetMapping(path = "/")
    public String mainPage(){
        return "This is Helping Hands app";
    }
}
