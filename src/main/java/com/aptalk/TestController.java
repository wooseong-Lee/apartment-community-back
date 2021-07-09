package com.aptalk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TestController {

    private final TestService testService;

    @GetMapping("/user")
    public List<TestUser> getAllUsers() {
        return testService.getAllUser();
    }

    @GetMapping("/user/{userId}")
    public TestUser getUserById(@PathVariable("userId") Long userId) {
        return testService.getUserById(userId);
    }

    @PostMapping("/user")
    public String joinUser(@RequestBody TestJoinForm testJoinForm) {
        TestUser testUser = new TestUser();
        testUser.setName(testJoinForm.getName());
        testUser.setAge(testJoinForm.getAge());

        log.info("name: "+ testUser.getName()+" age: "+testUser.getAge());

        testService.join(testUser);
        return testUser.getName()+" saved";
    }

}
