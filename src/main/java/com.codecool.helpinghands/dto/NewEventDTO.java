package com.codecool.helpinghands.dto;

import com.codecool.helpinghands.model.EventCategory;

import java.time.LocalDate;
import java.util.Set;
public class NewEventDTO {
    private int eventId;
    private String eventTitle;
    private EventCategory eventCategory;
    private String eventDescription;
    private String address;
    private String city;
    private String postalCode;
    private LocalDate dateOfEvent;
    private byte[] image;
    private Set<Integer[]> eventSlots;
}

