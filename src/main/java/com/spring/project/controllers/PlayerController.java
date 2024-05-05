package com.spring.project.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.spring.project.ProjectApplication;
import com.spring.project.entity.Player;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//https://www.iplt20.com/teams/sunrisers-hyderabad/results
@Controller
@RequestMapping("/player")
public class PlayerController {
    @RequestMapping(value = {"","/","/index"})
    public String index(Model model){
        ClassLoader classLoader=ProjectApplication.class.getClassLoader();
        try{
            String filePath=classLoader.getResource("data/players.json").getPath().substring(1);
            File file=new File(filePath);
            InputStream inputStream=new FileInputStream(file);
            Gson gson=new Gson();
            JsonReader jsonReader=new JsonReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
            jsonReader.setLenient(true);
            Type type= new TypeToken<ArrayList<Player>>(){}.getType();
            List<Player> players=gson.fromJson(jsonReader, type);
            model.addAttribute("players",players);
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
        return "player/index.html";
    }
}
