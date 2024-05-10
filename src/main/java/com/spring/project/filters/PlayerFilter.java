package com.spring.project.filters;

import lombok.Data;

import java.util.Optional;
@Data
public class PlayerFilter {
    private int teamId;
    private int typeId;
    private Optional<Boolean> indian=Optional.empty();
}
