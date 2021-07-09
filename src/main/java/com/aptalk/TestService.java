package com.aptalk;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    @Transactional
    public Long join(TestUser testUser) {
        testRepository.save(testUser);
        return testUser.getId();
    }

    public TestUser getUserById(Long userId) {
        return testRepository.findOneUserById(userId);
    }

    public List<TestUser> getAllUser() {
        return testRepository.findAllUser();
    }
}
