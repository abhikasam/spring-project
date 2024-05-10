package com.spring.project.service;

import com.spring.project.model.Player;
import com.spring.project.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private IPlayerRepository playerRepository;

    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

}
