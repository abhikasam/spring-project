package com.spring.project.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.spring.project.ProjectApplication;
import com.spring.project.entity.Player;
import com.spring.project.entity.Team;
import com.spring.project.filters.PlayerFilter;
import com.spring.project.shared.SelectListItem;
import jakarta.persistence.Tuple;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

//https://www.iplt20.com/teams/sunrisers-hyderabad/results
@Controller
@RequestMapping("/player")
public class PlayerController {
    private List<Player> players;
    private List<SelectListItem> teamSelectListItem;
    private PlayerFilter playerFilter=new PlayerFilter();
    @ModelAttribute("playFilter")
    public void setPlayerFilter(PlayerFilter playerFilter) {
        this.playerFilter = playerFilter;
    }

    private void setPlayers(){
        ClassLoader classLoader=ProjectApplication.class.getClassLoader();
        try{
            String filePath=classLoader.getResource("data/players.json").getPath().substring(1);
            File file=new File(filePath);
            InputStream inputStream=new FileInputStream(file);
            Gson gson=new Gson();
            JsonReader jsonReader=new JsonReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
            jsonReader.setLenient(true);
            Type type= new TypeToken<ArrayList<Player>>(){}.getType();
            this.players=gson.fromJson(jsonReader, type);
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }

    }
    private void setTeams(){
        ClassLoader classLoader= ProjectApplication.class.getClassLoader();
        try{
            String filePath=classLoader.getResource("data/teams.json").getPath().substring(1);
            File file=new File(filePath);
            InputStream inputStream=new FileInputStream(file);
            Gson gson=new Gson();
            JsonReader jsonReader=new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            jsonReader.setLenient(true);
            Type type= new TypeToken<ArrayList<Team>>(){}.getType();
            List<Team> teams=gson.fromJson(jsonReader, type);
            teamSelectListItem=teams.stream().map(i->{
                return new SelectListItem(i.getTeamName(),i.getTeamName(),playerFilter.getTeamName()!=null && playerFilter.getTeamName().equals(i.getTeamName()));
            }).toList();
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @RequestMapping(value = {"","/","/index"})
    public String index(Model model,Errors errors){
        setPlayers();
        setTeams();
        if(playerFilter.getTeamName()!=null && playerFilter.getTeamName().length()!=0){
            players=players.stream().filter(i->playerFilter.getTeamName().equals(i.getTeamName())).toList();
        }
        if(playerFilter.getType()!=null && playerFilter.getType().length()!=0){
            players=players.stream().filter(i-> Arrays.stream(i.getPlayerType()).anyMatch(k-> k.equals(playerFilter.getType()) )).toList();
        }
        if(playerFilter.getIndian()!=0){
            if(playerFilter.getIndian()==1){
                players=players.stream().filter(i-> i.isIndian()).toList();
            }
            else{
                players=players.stream().filter(i-> !i.isIndian()).toList();
            }
        }

        model.addAttribute("players",players);
        model.addAttribute("teamSelectListItem",teamSelectListItem);
        model.addAttribute("playerFilter",playerFilter);
        return "player/index";
    }
}
