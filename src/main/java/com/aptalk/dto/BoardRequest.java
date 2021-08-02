package com.aptalk.dto;

import lombok.Data;

@Data
public class BoardRequest {
    private final String title;
    private final String content;
}
