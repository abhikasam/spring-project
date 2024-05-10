package com.spring.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "playingtype")
public class PlayingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playingtypeid")
    private int playingTypeId;
    @Column(name = "playingtypename")
    private String playingTypeName;

    @OneToMany(mappedBy = "playingType")
    private List<PlayerType> playerTypes;
}
