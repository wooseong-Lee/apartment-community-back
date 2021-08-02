package com.aptalk.controller;

import com.aptalk.domain.Community;
import com.aptalk.dto.CommunityRequest;
import com.aptalk.dto.CommunityResponse;
import com.aptalk.repository.CommunityRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommunityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CommunityRepository communityRepository;

    @Test
    @Transactional(readOnly = true)
    @DisplayName("커뮤니티 목록 페이징 테스트")
    void getCommunitysByPageNum() throws Exception {

        // given
        String url = "/api/v1/aptalk/community?";
        String queryParameter = "page=0&size=5&sort=name,asc";

        // when
        ResultActions resultActions = mockMvc.perform(get(url + queryParameter))
                .andExpect(status().isOk());

        String responseJson = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        Map<String, Object> responsePage = objectMapper.readValue(responseJson,
                new TypeReference<Map<String, Object>>() {});

        List<Object> beforeConvertContent = (List<Object>) responsePage.get("content");
        List<CommunityResponse> afterConvertContent = new ArrayList<>();

        for (Object o : beforeConvertContent) {
            CommunityResponse communityResponse = objectMapper.convertValue(o, CommunityResponse.class);
            afterConvertContent.add(communityResponse);
        }

        // then
        assertThat(afterConvertContent.size()).isLessThanOrEqualTo(5);

        if (afterConvertContent.size() >= 2) {
            String firstCommunityName = afterConvertContent.get(0).getName();
            String secondCommunityName = afterConvertContent.get(1).getName();
            int compareValue = firstCommunityName.compareTo(secondCommunityName);

            assertThat(compareValue).isLessThanOrEqualTo(0);
        }

    }

    @Test
    @Transactional
    @DisplayName("커뮤니티 추가 테스트")
    void addCommunity() throws Exception {

        // given
        String url = "/api/v1/aptalk/community";
        String name = "<그룹> 그룹";
        String zipCode = "<우편번호> 13567";
        String notice = "<공지사항> 건물 외벽 공사관련 투표 진행하고 있습니다. 많은 참여 부탁드립니다.";
        CommunityRequest communityRequest = new CommunityRequest(name, zipCode, notice);

        // when
        long beforeAdd = communityRepository.count();

        mockMvc.perform(post(url)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(communityRequest)))
                .andExpect(status().isOk());

        long afterAdd = communityRepository.count();

        // then
        assertThat(afterAdd).isEqualTo(beforeAdd + 1);

    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("커뮤니티 상세 페이지 테스트")
    void getCommunity() throws Exception {

        // given
        String url = "/api/v1/aptalk/community/1";

        // when
        ResultActions resultActions = mockMvc.perform(get(url))
                .andExpect(status().isOk());

        String responseJson = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        CommunityResponse communityResponse = objectMapper.readValue(responseJson, CommunityResponse.class);
        Community community = communityRepository.findById(1L).get();

        // then
        assertThat(community.getId()).isEqualTo(communityResponse.getId());
        assertThat(community.getName()).isEqualTo(communityResponse.getName());
        assertThat(community.getNotice()).isEqualTo(communityResponse.getNotice());

    }

    @Test
    @Transactional
    @DisplayName("커뮤니티 수정 테스트")
    void updateCommunity() throws Exception {

        // given
        String url = "/api/v1/aptalk/community/1";
        String name = "<그룹> 그룹";
        String zipCode = "<우편번호> 13567";
        String notice = "<공지사항> 건물 외벽 공사관련 투표 진행하고 있습니다. 많은 참여 부탁드립니다.";
        CommunityRequest communityRequest = new CommunityRequest(name, zipCode, notice);

        // when
        mockMvc.perform(put(url)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(communityRequest)))
                .andExpect(status().isOk());

        // then
        Community afterUpdate = communityRepository.findById(1L).get();

        assertThat(afterUpdate.getName()).isEqualTo(name);
        assertThat(afterUpdate.getZipCode()).isEqualTo(zipCode);
        assertThat(afterUpdate.getNotice()).isEqualTo(notice);

    }

    @Test
    @Transactional
    @DisplayName("커뮤니티 삭제 테스트")
    void deleteCommunity() throws Exception {

        // given
        String url = "/api/v1/aptalk/community/1";

        // when
        mockMvc.perform(delete(url))
                .andExpect(status().isOk());

        // then
        assertThat(communityRepository.findById(1L).isEmpty()).isTrue();

    }
}