package com.spring.project.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class Team implements Serializable {
    private int teamId;
    @SerializedName("name")
    private String teamName;
    @SerializedName("owner")
    private String owner;
    @SerializedName("coach")
    private String coach;
    @SerializedName("venue")
    private String venue;
}
