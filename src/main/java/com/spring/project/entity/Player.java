package com.spring.project.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Player implements Serializable {
    public int playerId;
    @SerializedName("name")
    public String playerName;
    @SerializedName("team")
    public String playerTeam;
    @SerializedName("indian")
    public boolean isIndian;
    @SerializedName("type")
    public String[] playerType;
    public String getPlayerTypes(){
        return String.join(", ",playerType);
    }
}
