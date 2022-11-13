package com.codecool.helpinghands.controller;

import com.codecool.helpinghands.dto.EventDTO;
import com.codecool.helpinghands.dto.EventWithSlotsDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/events/{eventId}")
    public EventWithSlotsDTO getEventById(
            @PathVariable("eventId") int eventId
    ){
        Event event = eventService.getEventById(eventId);
        //return convertEventToEventDto(event);
        return convertEventToEventWithSlotsDto(event);
    }


    @PostMapping("/events")
    public EventDTO addEvent(
            @RequestParam("city") String city,
            @RequestParam("eventCategory") EventCategory eventCategory,
            @RequestParam("eventDescription") String eventDescription,
            @RequestParam("eventTitle") String eventTitle,
            @RequestParam("imagePath") String imagePath,
            @RequestParam("slotNum") int slotNum,
            @RequestParam("dateOfEvent") String dateOfEvent
    ){
        Event event = eventService.addEvent(city, eventCategory, eventDescription, eventTitle, imagePath, slotNum, dateOfEvent);
        return convertEventToEventDto(event);
    }

    @PostMapping("/events/{eventId}/slot")
    public EventWithSlotsDTO addSlotToEvent(
        @PathVariable("eventId") int eventId,
        @RequestParam ("slotStartHour") int slotStartHour,
        @RequestParam ("slotStartMinutes") int slotStartMinutes,
        @RequestParam ("slotEndHour") int slotEndHour,
        @RequestParam ("slotEndMinutes") int slotEndMinutes
    ){
        Event event = eventService.addSlotToEvent(eventId, slotStartHour, slotStartMinutes, slotEndHour, slotEndMinutes);
        return convertEventToEventWithSlotsDto(event);
    }

    @GetMapping(path = "/")
    public String mainPage(){
        return "This is Helping Hands app";
    }

    public EventDTO convertEventToEventDto(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    private EventWithSlotsDTO convertEventToEventWithSlotsDto(Event event) {
        EventWithSlotsDTO eventWithSlotsDto = modelMapper.map(event, EventWithSlotsDTO.class);
        eventService.addSlotsToList(eventWithSlotsDto);
        return eventWithSlotsDto;

    }
}


