package com.spring.project.service;

import com.spring.project.model.PlayingType;
import com.spring.project.repository.IPlayingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayingTypeService {
    @Autowired
    private IPlayingTypeRepository playingTypeRepository;
    public List<PlayingType> playingTypes(){
        return playingTypeRepository.findAll();
    }
}
