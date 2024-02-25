package com.nhnacademy.springboot.gateway.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropertiesTest {

    @Autowired
    private AccountProperties accountProperties;
    @Autowired
    private TaskProperties taskProperties;

    @Test
    @DisplayName("Account Properties 설정 확인")
    void testAccountProperties() {
        assertEquals("http://localhost:8081", accountProperties.getAddress());
    }

    @Test
    @DisplayName("Task Properties 설정 확인")
    void testTaskProperties() {
        assertEquals("http://localhost:8082", taskProperties.getAddress());
    }


}