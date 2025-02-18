package com.bjorn.gameloom.model;

import jakarta.persistence.*;
/*
 * https://store.steampowered.com/app/{appId}/ -> actual webpage of steam store.
 * 
 */
@Entity
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String logo;
    
    private String appId;

    public Long getId(){return id;}
    public void setId(Long id){ this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    
    public String getLogo(){return logo;}
    public void setLogo(String logo){this.logo = logo;}
    
    public String getAppId(){return appId;}
    public void setAppId(String appId){this.appId = appId;}

}
