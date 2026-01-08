package com.lizandronarvaez.spring_ai.services.impl;

import com.lizandronarvaez.spring_ai.services.OpenAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase: OpenAIServiceImplTest.java
 * Autor: narvaez
 * Fecha: 7/1/26
 */

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    private OpenAIService openAIService;

    @Test
    void getAnswer() {
        String answer=openAIService.getAnswer("Como puedo aprender ingles?");

        System.out.println(answer);
    }
}