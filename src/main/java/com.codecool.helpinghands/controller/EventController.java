package com.codecool.helpinghands.controller;

import com.codecool.helpinghands.dto.EventDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class EventController {

    private EventService eventService;
    private ModelMapper modelMapper;

    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/events")
    public List<EventDTO> getEvents(){
        return eventService.getAllEvents().stream()
                .map(this::convertEventToEventDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/event/{eventId}")
    public EventDTO getEventById(
            @PathVariable("eventId") int eventId
    ){
        Event event = eventService.getEventById(eventId).get();
        EventDTO eventDto = convertEventToEventDto(event);
        return eventDto;
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

    public EventDTO convertEventToEventDto(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }
}


