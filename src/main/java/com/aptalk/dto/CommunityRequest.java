package com.aptalk.dto;

import lombok.Data;

@Data
public class CommunityRequest {
    private final String name;
    private final String zipCode;
    private final String notice;
}
