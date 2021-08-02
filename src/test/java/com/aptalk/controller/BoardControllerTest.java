package com.aptalk.controller;

import com.aptalk.domain.Board;
import com.aptalk.dto.BoardRequest;
import com.aptalk.dto.BoardResponse;
import com.aptalk.repository.BoardRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional(readOnly = true)
    @DisplayName("게시판 목록 페이징 테스트")
    void getBoardsByPageNum() throws Exception {

        // given
        String url = "/api/v1/aptalk/community/1/board?";
        String queryParameter = "page=0&size=5&sort=createdAt,desc";

        // when
        ResultActions resultActions = mockMvc.perform(get(url + queryParameter))
                .andExpect(status().isOk());

        // 파싱 (응답 json -> Page<Object> -> List<BoardResponse>)
        String responseJson = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        Map<String, Object> responsePage = objectMapper.readValue(responseJson,
                new TypeReference<Map<String, Object>>() {});

        List<Object> beforeConvertContent = (List<Object>) responsePage.get("content");
        List<BoardResponse> afterConvertContent = new ArrayList<>();

        for (Object o : beforeConvertContent) {
            BoardResponse boardResponse = objectMapper.convertValue(o, BoardResponse.class);
            afterConvertContent.add(boardResponse);
        }

        // then
        assertThat(afterConvertContent.size()).isLessThanOrEqualTo(5);

        if (afterConvertContent.size() >= 2) {
            LocalDateTime firstBoardCreatedAt = afterConvertContent.get(0).getCreatedAt();
            LocalDateTime secondBoardCreatedAt = afterConvertContent.get(1).getCreatedAt();
            int compareValue = firstBoardCreatedAt.compareTo(secondBoardCreatedAt);

            assertThat(compareValue).isGreaterThanOrEqualTo(0);
        }

    }

    @Test
    @Transactional
    @DisplayName("게시판 추가 테스트")
    void addBoard() throws Exception {

        // given
        String url = "/api/v1/aptalk/community/1/board";
        String title = "<제목> 게시판 글 추가 테스트용입니다.";
        String content = "<본문> 게시판 글 추가 테스트용입니다.";
        BoardRequest boardRequest = new BoardRequest(title, content);

        // when
        long beforeAdd = boardRepository.count();

        mockMvc.perform(post(url)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardRequest)))
                .andExpect(status().isOk());

        long afterAdd = boardRepository.count();

        // then
        assertThat(afterAdd).isEqualTo(beforeAdd + 1);

    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("게시판 상세 페이지 테스트")
    void getBoard() throws Exception {

        // given
        String url = "/api/v1/aptalk/community/1/board/1";

        // when
        ResultActions resultActions = mockMvc.perform(get(url))
                .andExpect(status().isOk());

        String responseJson = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        BoardResponse boardResponse = objectMapper.readValue(responseJson, BoardResponse.class);
        Board board = boardRepository.findById(1L).get();

        // then
        assertThat(board.getId()).isEqualTo(boardResponse.getId());
        assertThat(board.getTitle()).isEqualTo(boardResponse.getTitle());
        assertThat(board.getContent()).isEqualTo(boardResponse.getContent());

    }

    @Test
    @Transactional
    @DisplayName("게시판 수정 테스트")
    void updateBoard() throws Exception {

        // given
        String url = "/api/v1/aptalk/community/1/board/1";
        String title = "<제목> 게시판 글 추가 테스트용입니다. (수정)";
        String content = "<본문> 게시판 글 추가 테스트용입니다. (수정)";
        BoardRequest boardRequest = new BoardRequest(title, content);

        // when
        mockMvc.perform(put(url)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardRequest)))
                .andExpect(status().isOk());

        // then
        Board afterUpdate = boardRepository.findById(1L).get();

        assertThat(afterUpdate.getTitle()).isEqualTo(title);
        assertThat(afterUpdate.getContent()).isEqualTo(content);

    }

    @Test
    @Transactional
    @DisplayName("게시판 삭제 테스트")
    void deleteBoard() throws Exception {

        // given
        String url = "/api/v1/aptalk/community/1/board/1";

        // when
        mockMvc.perform(delete(url))
                .andExpect(status().isOk());

        // then
        assertThat(boardRepository.findById(1L).isEmpty()).isTrue();

    }
}