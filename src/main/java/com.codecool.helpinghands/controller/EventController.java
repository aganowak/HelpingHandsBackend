package com.codecool.helpinghands.controller;

import com.codecool.helpinghands.dto.EventDTO;
import com.codecool.helpinghands.dto.EventWithSlotsDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.service.EventService;
import com.codecool.helpinghands.service.ImageDataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class EventController {


    private final EventService eventService;
    private final ImageDataService imageDataService;


    @Autowired
    public EventController(EventService eventService, ImageDataService imageDataService) {
        this.eventService = eventService;
        this.imageDataService = imageDataService;
    }


    @GetMapping("/events")
    public List<EventDTO> getEvents(){
        return eventService.getAllEventsAsEventDTO();
    }

    @GetMapping("/events/{eventId}")
    public EventWithSlotsDTO getEventById(
            @PathVariable("eventId") int eventId
    ){
        return eventService.getEventDtoByEventId(eventId);
    }


    @PostMapping("/events")
    public ResponseEntity<EventDTO> addEvent(
            //RequestBody
            @RequestParam("city") String city,
            @RequestParam("eventCategory") EventCategory eventCategory,
            @RequestParam("eventDescription") String eventDescription,
            @RequestParam("eventTitle") String eventTitle,
            @RequestParam("imagePath") String imagePath,
            @RequestParam("slotNum") int slotNum,
            @RequestParam("dateOfEvent") String dateOfEvent
    ){
        Event event = eventService.addEvent(city, eventCategory, eventDescription, eventTitle, imagePath, slotNum, dateOfEvent);
        EventDTO eventDTO = eventService.convertEventToEventDto(event);
        return new ResponseEntity<>(
                eventDTO,
                HttpStatus.OK);
    }

    @PostMapping("/events/{eventId}/slot")
    public ResponseEntity<EventWithSlotsDTO> addSlotToEvent(
        @PathVariable("eventId") int eventId,
        @RequestParam ("slotStartHour") int slotStartHour,
        @RequestParam ("slotStartMinutes") int slotStartMinutes,
        @RequestParam ("slotEndHour") int slotEndHour,
        @RequestParam ("slotEndMinutes") int slotEndMinutes
    ){
        Event event = eventService.addSlotToEvent(eventId, slotStartHour, slotStartMinutes, slotEndHour, slotEndMinutes);
        EventWithSlotsDTO eventWithSlotsDTO = eventService.convertEventToEventWithSlotsDto(event);
        return new ResponseEntity<>(
                eventWithSlotsDTO,
                HttpStatus.OK);
    }

    @PostMapping("/events/{eventId}/uploadPicture")
    public ResponseEntity<String> uploadImage(
            @PathVariable("eventId") int eventId,
            @RequestParam("image") MultipartFile file) throws IOException {
        Event event = eventService.getEventById(eventId);
        imageDataService.addPictureToEvent(file, event);
        return new ResponseEntity<>(
                "New image added",
                HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public String mainPage(){
        return "This is Helping Hands app";
    }

    @GetMapping("/events/city/{cityName}")
    public Optional<List<Event>> searchEventsByCity(@PathVariable("cityName") String cityName){
        return Optional.ofNullable(eventService.getEventsByCity(cityName));
    }

    @GetMapping("/events/category/{category}")
    public Optional<List<Event>> searchEventsByCategory(@PathVariable("category") String category){
        return Optional.ofNullable(eventService.getEventsByCategory(category));
    }

}


