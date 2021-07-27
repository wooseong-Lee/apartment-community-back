package com.aptalk.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommunityDto {
    private Long id;
    private String name;
    private String notice;
}
