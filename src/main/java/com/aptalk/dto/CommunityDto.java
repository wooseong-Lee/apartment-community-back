package com.aptalk.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommunityDto {
    private Long id;
    private String name;
    private String notice;
}
