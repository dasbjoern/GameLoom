package com.bjorn.gameloom.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjorn.gameloom.model.Game;
import com.bjorn.gameloom.model.User;
import com.bjorn.gameloom.model.UserGame;
import com.bjorn.gameloom.repository.GameRepository;
import com.bjorn.gameloom.repository.UserGameRepository;
import com.bjorn.gameloom.repository.UserRepository;

@Service
public class GameService {
    
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final UserGameRepository userGameRepository;

    public GameService(GameRepository gameRepository, UserGameRepository userGameRepository, UserRepository userRepository){
        this.gameRepository = gameRepository;
        this.userGameRepository = userGameRepository;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game saveGame(Game game){
        // Game savedGame = gameRepository.save(game);
        // saveUserGames( Long.valueOf(1), savedGame.getId()); //TEST
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

    //usergame ->

    @Transactional
    public boolean deleteUserGame(Game game){
        boolean deleted = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //Logged in user

        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByUsername(username).get();
            UserGame userGame = userGameRepository.findByUserAndGame(user, game);
            try{
                userGameRepository.delete(userGame);
                deleted = true;
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        return deleted;
    }

    public List<Game> getAllUserGames(){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //Logged in user
        String username = "";
        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        List<Game> games = userGameRepository.findAllByUser_Username(username).stream()
                                                                              .map(UserGame::getGame)
                                                                              .collect(Collectors.toList());
        return games;
         
    }
    public Game saveUserGames(Game game){ 

        if(!gameRepository.findByName(game.getName()).isPresent()){
            game = gameRepository.save(game);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //Logged in user

        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            
            UserGame userGame;
            try{
                
                userGame = userGameRepository.findByUser_UsernameAndGameId(username, game.getId());
            }catch(Exception e){
                System.out.println("\n" + e.getMessage());
                userGame = null;
            }
            if(userGame == null){
                try{
                    
                    User user = userRepository.findByUsername(username).get();
                    // Game game = gameRepository.findById(gameId).get();
                    userGame = new UserGame(user, game);
                    userGameRepository.save(userGame);
                    
                }catch(NoSuchElementException e){
                    System.err.println("UserGame could not be added. " + e.getMessage());
                }
            }else{
                System.out.println("User already added this game. ");
            }
        }
        return game;
    }
}
