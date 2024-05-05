package com.spring.project.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.spring.project.ProjectApplication;
import com.spring.project.entity.Player;
import com.spring.project.entity.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @RequestMapping(value={"","/","/index"})
    public String index(Model model){
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
            model.addAttribute("teams",teams);
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
        return "team/index.html";
    }
}
