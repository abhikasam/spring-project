package com.spring.project.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
@Data
public class Player implements Serializable {
    private int playerId;
    @SerializedName("name")
    private String playerName;
    @SerializedName("team")
    private String teamName;
    @SerializedName("indian")
    private boolean isIndian;
    @SerializedName("type")
    private String[] playerType;
    public String getPlayerTypes(){
        return String.join(", ",playerType);
    }
}
