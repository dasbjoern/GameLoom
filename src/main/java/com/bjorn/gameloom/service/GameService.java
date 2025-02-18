package com.bjorn.gameloom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.repository.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
}
