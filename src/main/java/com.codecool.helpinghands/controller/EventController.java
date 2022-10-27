package com.codecool.helpinghands.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @GetMapping(path = "/home")
    public String mainPage(){
        return "Helping hands main page";
    }
}
