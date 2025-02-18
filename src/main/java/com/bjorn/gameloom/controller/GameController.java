package com.bjorn.gameloom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.service.GameService;

//temporary
@RestController
@RequestMapping("/game")
public class GameController {
    
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }
}
