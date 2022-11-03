package com.codecool.helpinghands.repository;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

//    List<Event> EVENTS_IN_MEMORY = new ArrayList<>(
//            Arrays.asList(
//                    new Event(1,
//                            LocalDateTime.now(),
//                            "Feed the cats",
//                            "We will collect cat food in front of supermarkets",
//                            EventCategory.ANIMALS,
//                            "Warszawa",
//                            LocalDateTime.of(2023,01,01,12, 00),
//                             1,
//                            "static/EventPictures/event_1_photo.png"),
//                    new Event(2,
//                            LocalDateTime.now(), "Clean the park",
//                            "We will clean up the trash from the public park",
//                            EventCategory.ENVIRONMENT,
//                            "Krakow",
//                            LocalDateTime.of(2023,01,01,12, 00),2,
//                            "static/EventPictures/event_2_photo.png"),
//                    new Event(3,
//                            LocalDateTime.now(),
//                            "Winter clothes for the poor",
//                            "We will collect winter clothes for the homeless people.",
//                            EventCategory.PEOPLE,
//                            "Warszawa",
//                            LocalDateTime.of(2023,01,01,10, 00),
//                            4,
//                            "static/EventPictures/event_1_photo.png")));
}
