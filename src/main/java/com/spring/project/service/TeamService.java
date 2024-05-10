package com.spring.project.service;

import com.spring.project.model.Team;
import com.spring.project.repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    private ITeamRepository teamRepository;

    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

}
