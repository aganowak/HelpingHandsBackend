package com.codecool.helpinghands.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events_slot")
public class Slot {
    @Id
    @GeneratedValue
    private int slotId;
    @ManyToOne
    @JoinColumn
    private Event event;
    private LocalDateTime slotStartTime;
    private LocalDateTime slotEndTime;
    private int numOfVolunteers;
}
