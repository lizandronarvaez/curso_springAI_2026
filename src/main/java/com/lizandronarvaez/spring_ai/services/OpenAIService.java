package com.lizandronarvaez.spring_ai.services;

import com.lizandronarvaez.spring_ai.dto.*;

/**
 * Clase: OpenAIService.java
 */
public interface OpenAIService {

    String getAnswer(String question);
    MessageResponse getAnswer(Question question);
    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);
    MessageResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
}
