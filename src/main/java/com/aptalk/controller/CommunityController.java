package com.aptalk.controller;

import com.aptalk.domain.Community;
import com.aptalk.dto.CommunityRequest;
import com.aptalk.dto.CommunityResponse;
import com.aptalk.repository.CommunityRepository;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/aptalk/community")
public class CommunityController {
    private final CommunityRepository communityRepository;

    @GetMapping
    public Page<CommunityResponse> getCommunitys(Pageable pageable) {
        return communityRepository.findAll(pageable)
                .map(CommunityResponse::newResponse);
    }

    @PostMapping
    public Community createCommunity(@RequestBody CommunityRequest communityRequest) {
        Community community = Community.from(communityRequest);

        try {
            return communityRepository.save(community);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("해당 커뮤니티가 이미 존재합니다.");
        }
    }

    @GetMapping("/{communityId}")
    public CommunityResponse getCommunity(@ApiParam(value = "커뮤니티 번호") @PathVariable Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()-> new NoSuchElementException("해당 커뮤니티가 존재하지 않습니다."));

        return CommunityResponse.newResponse(community);
    }

    @PutMapping("/{communityId}")
    public Community updateCommunity(@ApiParam(value = "커뮤니티 번호") @PathVariable Long communityId,
                                     @RequestBody CommunityRequest communityRequest) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()-> new NoSuchElementException("해당 커뮤니티가 존재하지 않습니다."));

        return community.updateCommunity(communityRequest);
    }

    @DeleteMapping("/{communityId}")
    public void deleteCommunity(@ApiParam(value = "커뮤니티 번호") @PathVariable Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()-> new NoSuchElementException("해당 커뮤니티가 존재하지 않습니다."));

        communityRepository.delete(community);
    }
}

