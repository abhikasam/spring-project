package com.spring.project.controllers;

import com.spring.project.service.PlayerService;
import com.spring.project.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;
    @RequestMapping(value={"","/","/index"})
    public String index(Model model){
        model.addAttribute("teams",teamService.getTeams());
        return "team/index.html";
    }

    @RequestMapping("/players")
    public String getPlayers(Model model,@RequestParam Optional<Integer> teamId){
        var players=playerService.getPlayers();
        if(teamId.isPresent()){
            players=players.stream().filter(i->teamId.get()==i.getTeam().getTeamId()).toList();
        }
        model.addAttribute("players",players);
        return "team/players.html";
    }
}
