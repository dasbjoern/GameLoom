package com.gameloom.GameLoom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {
    
    @GetMapping("/")
        public String homePage(){
            return "home";
        }
}
