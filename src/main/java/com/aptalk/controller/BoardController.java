package com.aptalk.controller;

import com.aptalk.domain.Board;
import com.aptalk.dto.BoardRequest;
import com.aptalk.dto.BoardResponse;
import com.aptalk.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/aptalk/community/{communityId}/board")
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("")
    public Page<BoardResponse> getBoardsByPageNum(@PathVariable Long communityId, Pageable pagable) {
        return boardRepository.findAllByCommunityId(communityId, pagable)
                .map(BoardResponse::newResponse);
    }

    @PostMapping("")
    public void addBoard(@PathVariable Long communityId, @RequestBody BoardRequest boardRequest) {
        Board board = Board.newBoard(communityId, boardRequest);

        boardRepository.save(board);
    }

    @GetMapping("/{boardId}")
    public Board getBoard(@PathVariable Long boardId) {
        return boardRepository.findById(boardId).get();
    }

    @PutMapping("/{boardId}")
    public void updateBoard(@PathVariable Long boardId,
                            @RequestBody BoardRequest boardRequest) {
        Board board = boardRepository.findById(boardId).get();
        board.updateBoard(boardRequest);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        boardRepository.delete(board);
    }
}
