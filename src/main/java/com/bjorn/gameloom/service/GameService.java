package com.bjorn.gameloom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.repository.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game saveGame(Game game){
        return gameRepository.save(game);
    }

    @Transactional
    public boolean deleteGame(Game game){
        boolean deleted = false;
        try{
            gameRepository.delete(game);
            deleted = true;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return deleted;
    }
}
