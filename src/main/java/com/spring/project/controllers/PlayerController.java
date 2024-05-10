package com.spring.project.controllers;

import com.spring.project.filters.PlayerFilter;
import com.spring.project.model.Player;
import com.spring.project.service.PlayerService;
import com.spring.project.service.PlayerTypeService;
import com.spring.project.service.PlayingTypeService;
import com.spring.project.service.TeamService;
import com.spring.project.shared.SelectListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//https://www.iplt20.com/teams/sunrisers-hyderabad/results
@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayingTypeService playingTypeService;
    private List<SelectListItem> teamSelectList;
    private List<SelectListItem> playingTypeSelectList;
    private PlayerFilter playerFilter=new PlayerFilter();
    private List<Player> players;
    @ModelAttribute("playFilter")
    public void setPlayerFilter(PlayerFilter playerFilter) {
        this.playerFilter = playerFilter;
    }

    @RequestMapping(value = {"","/","/index"})
    public String index(Model model,Errors errors){
        populateSelectList(model);
        players=playerService.getPlayers();
        applyFilters();

        model.addAttribute("players",players);
        model.addAttribute("playerFilter",playerFilter);
        return "player/index";
    }

    public void populateSelectList(Model model){
        teamSelectList=teamService.getTeams().stream().map(i->{
            return new SelectListItem(i.getTeamName(),String.valueOf(i.getTeamId()),playerFilter.getTeamId()==i.getTeamId());
        }).toList();
        model.addAttribute("teamSelectList",teamSelectList);

        playingTypeSelectList=playingTypeService.playingTypes().stream().map(i->{
            return new SelectListItem(i.getPlayingTypeName(),String.valueOf(i.getPlayingTypeId()),playerFilter.getTypeId()==i.getPlayingTypeId());
        }).toList();
        model.addAttribute("playingTypeSelectList",playingTypeSelectList);
    }

    public void applyFilters(){
        if(playerFilter.getTeamId()!=0){
            players=players.stream().filter(i->playerFilter.getTeamId()==i.getTeam().getTeamId()).toList();
        }

        if(playerFilter.getTypeId()!=0){
            players=players.stream()
                           .filter(i-> i.playingTypeIds().contains(playerFilter.getTypeId()))
                           .toList();
        }

        if(playerFilter.getIndian().isPresent()){
            players=players.stream().filter(i-> i.isIndian()==playerFilter.getIndian().get()).toList();
        }
    }

}
