package com.aptalk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceTest {

    TestService testService = new TestService();

    @Test
    @DisplayName("테스트")
    void test() {
        assertEquals("Aptalk", testService.getTestResult());
    }
}
