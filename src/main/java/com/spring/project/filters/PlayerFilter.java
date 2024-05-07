package com.spring.project.filters;

import lombok.Data;

import java.util.Optional;

@Data
public class PlayerFilter {
    private String teamName;
    private String type;
    private int indian;
}
