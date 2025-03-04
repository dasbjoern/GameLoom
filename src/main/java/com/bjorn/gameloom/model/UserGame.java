package com.bjorn.gameloom.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class UserGame {
    
    public UserGame(){}
    
    public UserGame(User user, Game game){
        this.user = user;
        this.game = game;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    
    // private Long userId;

    @ManyToOne()
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    private Game game;
    // private List<Long> likedGames;
    
    public Long getId(){
        return this.id;
    }
    // public void setId(Long id){
    //     this.id = id;
    // }
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Game getGame(){
        return this.game;
    }
    public void setGame(Game game){
        this.game = game;
    }

    // public List<Long> getLikedGames(){
    //     return this.likedGames;
    // }
    // public void setLikedGames(List<Long> likedGames){
    //     this.likedGames = likedGames;
    // }
    // public void addLikedGame(Long gameId){
    //     this.likedGames.add(gameId);
    // }
}
