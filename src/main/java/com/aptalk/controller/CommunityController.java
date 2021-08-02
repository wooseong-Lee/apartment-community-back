package com.aptalk.controller;

import com.aptalk.domain.Community;
import com.aptalk.dto.CommunityRequest;
import com.aptalk.dto.CommunityResponse;
import com.aptalk.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/aptalk/community")
public class CommunityController {
    private final CommunityRepository communityRepository;

    @GetMapping("")
    public Page<CommunityResponse> getCommunitysByPageNum(Pageable pagable) {
        return communityRepository.findAll(pagable)
                .map(CommunityResponse::newResponse);
    }

    @PostMapping("")
    public void addCommunity(@RequestBody CommunityRequest communityRequest) {
        Community community = Community.newCommunity(communityRequest);
        communityRepository.save(community);
    }

    @GetMapping("/{communityId}")
    public CommunityResponse getCommunity(@PathVariable Long communityId) {
        Community community = communityRepository.findById(communityId).get();
        return CommunityResponse.newResponse(community);
    }

    @PutMapping("/{communityId}")
    public void updateCommunity(@PathVariable Long communityId,
                                @RequestBody CommunityRequest communityRequest) {
        Community community = communityRepository.findById(communityId).get();
        community.updateCommunity(communityRequest);
    }

    @DeleteMapping("/{communityId}")
    public void deleteCommunity(@PathVariable Long communityId) {
        Community community = communityRepository.findById(communityId).get();
        communityRepository.delete(community);
    }
}

