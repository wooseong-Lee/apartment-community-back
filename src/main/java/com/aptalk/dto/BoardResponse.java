package com.aptalk.dto;

import com.aptalk.domain.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static BoardResponse newResponse(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedAt(),
                board.getModifiedAt()
        );
    }
}
