package com.bjorn.gameloom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.model.SteamGame;
import com.bjorn.gameloom.model.SteamResponse;
import com.bjorn.gameloom.service.GameService;
import com.bjorn.gameloom.service.SteamApiService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FrontendController {

    // @Autowired
    private SteamApiService steamApiService;
    // @Autowired
    // private GameService gameService;

    public FrontendController(SteamApiService steamApiService){
        this.steamApiService = steamApiService;
    }

    @GetMapping("/hello")
    public List<SteamGame> sayHello() {
        Mono<SteamResponse> response = steamApiService.fetchApiData("?filter=globaltopsellers&ignore_preferences=1&json=1");
            List<SteamGame> games = response.block().getItems();
        return games;
    }

    // @GetMapping("/games")
    // public List<Game> addedGames(){
    
    //     return gameService.getAllGames();
    // }
    
}
