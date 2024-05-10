package com.spring.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "playertype")
public class PlayerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playertypeid")
    private int playerTypeId;

    @ManyToOne
    @JoinColumn(name = "playerid")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "playingtypeid")
    private PlayingType playingType;
}
