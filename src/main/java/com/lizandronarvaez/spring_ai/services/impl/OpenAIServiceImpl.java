package com.lizandronarvaez.spring_ai.services.impl;

import com.lizandronarvaez.spring_ai.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

/**
 * Clase: OpenAIServiceImpl.java
 */

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatModel.call(prompt);

        return chatResponse.getResult().getOutput().getText();
    }
}
