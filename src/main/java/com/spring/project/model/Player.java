package com.spring.project.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerid")
    private int playerId;

    @Column(name = "playername")
    private String playerName;

    @Column(name = "isindian")
    private boolean isIndian;

    @ManyToOne
    @JoinColumn(name = "teamid")
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<PlayerType> playerTypes;

    public List<Integer> playingTypeIds(){
        if(playerTypes!=null){
            return playerTypes.stream().map(i->i.getPlayingType().getPlayingTypeId()).toList();
        }
        return new ArrayList<>();
    }

    public List<String> playingTypes(){
        if(playerTypes!=null){
            return playerTypes.stream().map(i->i.getPlayingType().getPlayingTypeName())
                    .toList();
        }
        return new ArrayList<>();
    }

    public String allPlayingTypes(){
        return String.join(", ",playingTypes());
    }

    // Getters and setters
}
