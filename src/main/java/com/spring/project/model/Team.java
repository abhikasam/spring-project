package com.spring.project.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamid")
    private int teamId;

    @Column(name = "teamname")
    private String teamName;

    @Column(name="teamcode",nullable = false)
    private String teamCode;

    @Lob
    @Column(name = "teamimage")
    private byte[] teamImage;

    @Column(name = "owner")
    private String owner;

    @Column(name = "coach")
    private String coach;

    @Column(name = "venue")
    private String venue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "captainid")
    private Player captain;

    @OneToMany(mappedBy = "team",targetEntity = Player.class)
    private Set<Player> players;
    // Getters and setters
}
