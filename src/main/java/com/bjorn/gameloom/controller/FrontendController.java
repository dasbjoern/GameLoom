package com.bjorn.gameloom.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.model.SteamGame;
import com.bjorn.gameloom.model.SteamResponse;
import com.bjorn.gameloom.service.GameService;
import com.bjorn.gameloom.service.SteamApiService;

import io.micrometer.observation.transport.RequestReplySenderContext;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api", method = {RequestMethod.GET, RequestMethod.POST})
@CrossOrigin(origins = "http://localhost:3000")
public class FrontendController {

    // @Autowired
    private final SteamApiService steamApiService;
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

    @PostMapping("/search") //error
    public List<SteamGame> searchSteam(@RequestBody Map<String, String> searchText){

        System.out.println("\n" + searchText.get("searchsteam"));
        String term = URLEncoder.encode(searchText.get("searchsteam"), StandardCharsets.UTF_8);
        Mono<SteamResponse> response = null;
        List<SteamGame> games = new ArrayList<>();
        
        if(!searchText.get("searchsteam").equals(null)){
            response = steamApiService.fetchApiData("?term="+term+"&json=1");
            games = response.block().getItems();
        }else{
            games.add(new SteamGame()); //no result
        }

        return games;
    }
    // @GetMapping("/games")
    // public List<Game> addedGames(){
    
    //     return gameService.getAllGames();
    // }
    
}
