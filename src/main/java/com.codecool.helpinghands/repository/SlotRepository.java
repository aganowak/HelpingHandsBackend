package com.codecool.helpinghands.repository;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

//    List<Slot> SLOTS_IN_MEMORY = new ArrayList<>(
//            Arrays.asList(
//                    new Slot(1,
//                            1,
//                            LocalDateTime.of(2023,01,01,12, 00),
//                            LocalDateTime.of(2023,01,01,18, 00),
//                            10),
//                    new Slot(2,
//                            2,
//                            LocalDateTime.of(2023,01,01,12, 00),
//                            LocalDateTime.of(2023,01,01,15, 00),
//                            15),
//                    new Slot(3,
//                            2,
//                            LocalDateTime.of(2023,01,01,15, 00),
//                            LocalDateTime.of(2023,01,01,18, 00),
//                            15),
//                    new Slot(4,
//                            3,
//                            LocalDateTime.of(2023,01,01,10, 00),
//                            LocalDateTime.of(2023,01,01,12, 00),
//                            3),
//                    new Slot(5,
//                            3,
//                            LocalDateTime.of(2023,01,01,12, 00),
//                            LocalDateTime.of(2023,01,01,14, 00),
//                            3),
//                    new Slot(6,
//                            3,
//                            LocalDateTime.of(2023,01,01,14, 00),
//                            LocalDateTime.of(2023,01,01,16, 00),
//                            3),
//                    new Slot(7,
//                            3,
//                            LocalDateTime.of(2023,01,01,16, 00),
//                            LocalDateTime.of(2023,01,01,18, 00),
//                            3)));

}
