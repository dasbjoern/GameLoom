package com.bjorn.gameloom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bjorn.gameloom.model.SteamGame;
import com.bjorn.gameloom.model.SteamResponse;

import reactor.core.publisher.Mono;

@Service
public class SteamApiService {
    
    private final WebClient webClient;

    public SteamApiService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("https://store.steampowered.com/search/results/").build();
    }

    public Mono<SteamResponse> fetchApiData(String endpoint){

        return webClient.get()//.accept(MediaType.APPLICATION_JSON)
                        .uri(endpoint)
                        .retrieve()
                        .bodyToMono(SteamResponse.class);
                        // .collectList(); //sync/ blocking. For async use Mono<String>
    }
}
