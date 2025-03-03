package com.bjorn.gameloom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.model.UserGames;
import com.bjorn.gameloom.repository.GameRepository;
import com.bjorn.gameloom.repository.UserGamesRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserGamesRepository userGamesRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game saveGame(Game game){
        Game savedGame = gameRepository.save(game);
        saveUserGames( Long.valueOf(1), savedGame.getId()); //TEST
        return savedGame;
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

    public void saveUserGames(Long userId, Long gameId){ //TEST
        UserGames userGames;
        try{

            userGames = userGamesRepository.findByUserId(userId);
        }catch(Exception e){
            System.out.println("\n" + e.getMessage());
            userGames = null;
        }
        if(userGames == null){
                userGames = new UserGames(userId, gameId);
        }else{
                userGames.addLikedGame(gameId);
            }
        userGamesRepository.save(userGames);
    }
}
