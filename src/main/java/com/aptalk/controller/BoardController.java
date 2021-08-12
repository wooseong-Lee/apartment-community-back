package com.aptalk.controller;

import com.aptalk.domain.Board;
import com.aptalk.dto.BoardRequest;
import com.aptalk.dto.BoardResponse;
import com.aptalk.excetption.NotAllowedException;
import com.aptalk.repository.BoardRepository;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/aptalk/community/{communityId}/board")
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping
    public Page<BoardResponse> getBoards(@ApiParam(value = "커뮤니티 번호") @PathVariable Long communityId,
                                         Pageable pagable) {
        return boardRepository.findAllByCommunityId(communityId, pagable)
                .map(BoardResponse::newResponse);
    }

    @PostMapping
    public Board createBoard(@ApiParam(value = "커뮤니티 번호") @PathVariable Long communityId,
                             @RequestBody BoardRequest boardRequest) {
        Board board = Board.of(communityId, boardRequest);
        return boardRepository.save(board);
    }

    @GetMapping("/{boardId}")
    public Board getBoard(@ApiParam(value = "커뮤니티 번호") @PathVariable Long communityId,
                          @ApiParam(value = "게시글 번호") @PathVariable Long boardId) throws NotAllowedException {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));

        Long boardCommunityId = board.getCommunity().getId();
        if (communityId.equals(boardCommunityId)) return board;
        else throw new NotAllowedException("해당 게시글에 접근할 수 없습니다.");
    }

    @PutMapping("/{boardId}")
    public Board updateBoard(@ApiParam(value = "커뮤니티 번호") @PathVariable Long communityId,
                             @ApiParam(value = "게시글 번호") @PathVariable Long boardId,
                             @RequestBody BoardRequest boardRequest) throws NotAllowedException {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));

        Long boardCommunityId = board.getCommunity().getId();
        if (communityId.equals(boardCommunityId)) return board.updateBoard(boardRequest);
        else throw new NotAllowedException("해당 게시글에 접근할 수 없습니다.");
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@ApiParam(value = "커뮤니티 번호") @PathVariable Long communityId,
                            @ApiParam(value = "게시글 번호") @PathVariable Long boardId) throws NotAllowedException {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));

        Long boardCommunityId = board.getCommunity().getId();
        if (communityId.equals(boardCommunityId)) boardRepository.delete(board);
        else throw new NotAllowedException("해당 게시글에 접근할 수 없습니다.");
    }
}