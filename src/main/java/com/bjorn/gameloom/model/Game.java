package com.bjorn.gameloom.model;

import java.util.List;

import jakarta.persistence.*;
/*
 * https://store.steampowered.com/app/{appId}/ -> actual webpage of steam store.
 * 
 */
@Entity
public class Game { //Add DTOs
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    
    private String logo;
    
    private String appId;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true) // Removes UserGame if game is removed.
    private List<UserGame> userGames;
    
    public Long getId(){return id;}
    public void setId(Long id){ this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    
    public String getLogo(){return logo;}
    public void setLogo(String logo){this.logo = logo;}
    
    public String getAppId(){return appId;}
    public void setAppId(String appId){this.appId = appId;}

}
