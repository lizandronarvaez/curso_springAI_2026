package com.lizandronarvaez.spring_ai.controllers;

import com.lizandronarvaez.spring_ai.dto.*;
import com.lizandronarvaez.spring_ai.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Clase: QuestionController.java
 * Autor: narvaez
 * Fecha: 8/1/26
 */

@RestController
@RequestMapping("/open-ai")
@RequiredArgsConstructor
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping("/capitalWithInfo")
    public MessageResponse getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest) {

        return openAIService.getCapitalWithInfo(getCapitalRequest);
    }

    @PostMapping("/capital")
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest getCapitalRequest) {

        return openAIService.getCapital(getCapitalRequest);
    }

    @PostMapping("/question")
    public MessageResponse getQuestion(@RequestBody Question question) {

        return openAIService.getAnswer(question);
    }
}
