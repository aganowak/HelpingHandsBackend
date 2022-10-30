package com.codecool.helpinghands.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private int eventId;
    private LocalDateTime dateCreated;
    private String eventTitle;
    private String eventDescription;
    private EventCategory eventCategory;
    private String city;
    private LocalDateTime dateOfEvent;
    private int numberOfSlots;
    private String imagePath;
}
