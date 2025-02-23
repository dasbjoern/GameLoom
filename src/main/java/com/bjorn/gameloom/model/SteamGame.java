package com.bjorn.gameloom.model;

public class SteamGame {

    // private int id;

    private String name;
    private String logo;
    
    public SteamGame(){
        this.name = "No Result";
        this.logo = "";
    }
    // public int getId(){return id;}
    // public void setId(int id){ this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    
    public String getLogo(){return logo;}
    public void setLogo(String logo){this.logo = logo;}
    
}
