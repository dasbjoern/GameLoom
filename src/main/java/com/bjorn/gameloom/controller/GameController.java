package com.bjorn.gameloom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.model.SteamGame;
import com.bjorn.gameloom.model.SteamResponse;
import com.bjorn.gameloom.service.GameService;
import com.bjorn.gameloom.service.SteamApiService;

import reactor.core.publisher.Mono;

//temporary
@RestController
public class GameController {
    
    @Autowired
    private GameService gameService;
    private SteamApiService steamApiService;

    public GameController(SteamApiService steamApiService){
        this.steamApiService = steamApiService;
    }
    @GetMapping("/game")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

     @PostMapping("/welcome")
        public String homePostPage(){
            // Mono<String> responseMono = steamApiService.fetchApiData("/?filter=globaltopsellers&ignore_preferences=1&json=1");
            // responseMono.subscribe(System.out::println);
            Mono<SteamResponse> response = steamApiService.fetchApiData("?filter=globaltopsellers&ignore_preferences=1&json=1");
            // response.flatMap(games -> Mono.justOrEmpty(2 < games.size() ? games.get(2) : null))
            List<SteamGame> games = response.block().getItems();
            String rtrn = "";
            // System.out.println(games.get(0).getName());

            if(games.equals(null))
                rtrn = "null";
            else
                rtrn = games.get(0).getName();

            return rtrn;
        }
}
