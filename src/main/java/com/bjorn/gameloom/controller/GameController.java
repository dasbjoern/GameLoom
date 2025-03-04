package com.bjorn.gameloom.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/data/games")
public class GameController {
    
    @Autowired
    private GameService gameService;

    // private SteamApiService steamApiService;

    // public GameController(SteamApiService steamApiService){
    //     this.steamApiService = steamApiService;
    // }

    @GetMapping("/all")
    public List<Game> getAllAddedGames(){
        return gameService.getAllGames();
    }
    @GetMapping("/allUserGames")
    public List<Game> getAllUserGames(){
        // gameService.getAllGames();
        return gameService.getAllUserGames();
    }

    @PostMapping("/add")
    public Game saveGame(@RequestBody Game game){
        return gameService.saveGame(game);
    }
    @PostMapping("/del")
    public boolean delGame(@RequestBody Game game){
        System.out.println("\n DELETING" + game.getName());

        return gameService.deleteGame(game);
    }
    @PostMapping("/addUserGame")
    public Game addUserGame(@RequestBody Game game){
        System.out.println("\n ADD UserGame" + game.getName());

        return gameService.saveUserGames(game);
    }
    @PostMapping("/delUserGame")
    public boolean delUserGame(@RequestBody Game game){
        System.out.println("\n DELETING UserGame" + game.getName());

        return gameService.deleteUserGame(game);
    }


    //  @PostMapping("/welcome")
    //     public String homePostPage(){
    //         Mono<SteamResponse> response = steamApiService.fetchApiData("?filter=globaltopsellers&ignore_preferences=1&json=1");
    //         // response.flatMap(games -> Mono.justOrEmpty(2 < games.size() ? games.get(2) : null))
    //         List<SteamGame> games = response.block().getItems();
    //         String rtrn = "";
    //         // System.out.println(games.get(0).getName());

    //         if(games.equals(null))
    //             rtrn = "null";
    //         else
    //             rtrn = games.get(0).getName();

    //         return rtrn;
    //     }
}
