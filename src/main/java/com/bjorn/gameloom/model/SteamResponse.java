package com.bjorn.gameloom.model;

import java.util.List;

public class SteamResponse {
    private String desc;
    private List<SteamGame> items;

    // Getters and Setters
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public List<SteamGame> getItems() { return items; }
    public void setItems(List<SteamGame> items) { this.items = items; }
}
