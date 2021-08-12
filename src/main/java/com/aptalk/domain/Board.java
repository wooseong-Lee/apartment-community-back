package com.aptalk.domain;

import com.aptalk.dto.BoardRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Column(nullable = false)
    private String title;

    private String content;

    private Board(Community community, String title, String content) {
        this.community = community;
        this.title = title;
        this.content = content;
    }

    public static Board of(Long communityId, BoardRequest boardRequest) {
        return new Board(new Community(communityId), boardRequest.getTitle(), boardRequest.getContent());
    }

    public Board updateBoard(BoardRequest boardRequest) {
        if (boardRequest.getTitle() != null) this.title = boardRequest.getTitle();
        if (boardRequest.getContent() != null) this.content = boardRequest.getContent();
        return this;
    }
}
