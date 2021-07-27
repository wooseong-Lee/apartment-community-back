package com.aptalk;

import com.aptalk.testapi.TestRepository;
import com.aptalk.testapi.TestService;
import com.aptalk.testapi.TestUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class TestRepositoryTest {

    @Autowired
    TestService testService;

    @Autowired
    TestRepository testRepository;

    @Test
    void joinUser() {
        TestUser testUser = new TestUser();
        testUser.setAge(15);
        testUser.setName("홍길동");

        Long savedUserId = testService.join(testUser);

        assertEquals(testUser, testRepository.findOneUserById(savedUserId));
    }
}