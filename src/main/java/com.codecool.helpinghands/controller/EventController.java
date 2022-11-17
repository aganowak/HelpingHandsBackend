package com.codecool.helpinghands.controller;

import com.codecool.helpinghands.dto.EventDTO;
import com.codecool.helpinghands.dto.EventWithSlotsDTO;
import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import com.codecool.helpinghands.service.EventService;
import com.codecool.helpinghands.service.ImageDataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class EventController {


    private final EventService eventService;
    private final ModelMapper modelMapper;
    private final ImageDataService imageDataService;


    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper, ImageDataService imageDataService) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
        this.imageDataService = imageDataService;
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

    @PostMapping("/events/{eventId}/uploadPicture")
    public void uploadImage(
            @PathVariable("eventId") int eventId,
            @RequestParam("image") MultipartFile file) throws IOException {
        Event event = eventService.getEventById(eventId);
        imageDataService.addPictureToEvent(file, event);
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
        //addPhotoToEventWithSlotsDto(eventWithSlotsDto);
        return eventWithSlotsDto;
    }

    public void addPhotoToEventWithSlotsDto (EventWithSlotsDTO eventWithSlotsDto) {
        int eventId = eventWithSlotsDto.getEventId();
        Event event = eventService.getEventById(eventId);
        byte[] eventPhoto = imageDataService.getImageByEventId(event);
        eventWithSlotsDto.setImage(eventPhoto);
    }
}


