package com.aptalk.dto;

import com.aptalk.domain.Community;
import lombok.Data;

@Data
public class CommunityResponse {
    private final Long id;
    private final String name;
    private final String notice;

    public static CommunityResponse newResponse(Community community) {
        return new CommunityResponse(community.getId(), community.getName(), community.getNotice());
    }
}
