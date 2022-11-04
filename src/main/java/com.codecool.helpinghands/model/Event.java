package com.codecool.helpinghands.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private int eventId;
    private LocalDateTime dateCreated;
    private String eventTitle;
    private String eventDescription;
    @Enumerated
    private EventCategory eventCategory;
    private String city;
    private LocalDate dateOfEvent;
    private int numberOfSlots;
    private String imagePath;
    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private Set<Slot> eventSlots = new HashSet<>();

    public Event(String eventTitle, String eventDescription, EventCategory eventCategory, String city, int numberOfSlots, String imagePath, LocalDate dateOfEvent) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventCategory = eventCategory;
        this.city = city;
        this.numberOfSlots = numberOfSlots;
        this.imagePath = imagePath;
        this.dateOfEvent = dateOfEvent;
        this.dateCreated = LocalDateTime.now();
    }
}
