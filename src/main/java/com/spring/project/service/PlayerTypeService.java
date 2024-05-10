package com.spring.project.service;

import com.spring.project.model.PlayerType;
import com.spring.project.repository.IPlayerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerTypeService {
    @Autowired
    private IPlayerTypeRepository playerTypeRepository;
    public List<PlayerType> getPlayerTypes(){
        return playerTypeRepository.findAll();
    }
}
