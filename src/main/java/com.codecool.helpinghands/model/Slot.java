package com.codecool.helpinghands.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot {
    private int slotId;
    private int eventId;
    private LocalDateTime slotStartTime;
    private LocalDateTime slotEndTime;
    private int numOfVolunteers;
}
