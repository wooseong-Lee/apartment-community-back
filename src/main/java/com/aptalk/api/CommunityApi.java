package com.aptalk.api;

import com.aptalk.domain.Community;
import com.aptalk.dto.CommunityDto;
import com.aptalk.dto.BoardDto;
import com.aptalk.repository.CommunityRepository;
import com.aptalk.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/aptalk/community")
public class CommunityApi {
    private final CommunityRepository communityRepository;
    private final BoardRepository boardRepository;

    @GetMapping("/{communityId}")
    public CommunityDto getCommunityInfo(@PathVariable Long communityId) {
        Community community = communityRepository.getById(communityId);
        CommunityDto communityDto = CommunityDto.builder()
                .id(community.getId())
                .name(community.getName())
                .notice(community.getNotice())
                .build();
        return communityDto;
    }

    @GetMapping("/{communityId}/board")
    public Page<BoardDto> getBoardsByPageNum(@PathVariable Long communityId, Pageable pagable) {
        return boardRepository.findAllByCommunityId(communityId, pagable)
                .map(board -> BoardDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .createdAt(board.getCreatedAt())
                        .modifiedAt(board.getModifiedAt())
                        .build()
                );
    }

//    @GetMapping("/{communityId}/board/{boardId}")
}

