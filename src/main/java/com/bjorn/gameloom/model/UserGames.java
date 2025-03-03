package com.bjorn.gameloom.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserGames {
    
    public UserGames(){}
    
    public UserGames(Long userId, Long gameId){
        this.userId = userId;
        likedGames = new ArrayList<>();
        likedGames.add(gameId);
    }

    @Id
    private Long userId;

    private List<Long> likedGames;
    
    public Long getUserId(){
        return this.userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public List<Long> getLikedGames(){
        return this.likedGames;
    }
    public void setLikedGames(List<Long> likedGames){
        this.likedGames = likedGames;
    }
    public void addLikedGame(Long gameId){
        this.likedGames.add(gameId);
    }
}
