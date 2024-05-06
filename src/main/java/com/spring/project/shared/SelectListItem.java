package com.spring.project.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectListItem {
    private String text;
    private String value;
    private boolean isSelected;
}
