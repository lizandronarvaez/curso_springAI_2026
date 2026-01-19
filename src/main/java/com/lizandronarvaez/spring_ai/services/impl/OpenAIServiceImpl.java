package com.lizandronarvaez.spring_ai.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizandronarvaez.spring_ai.dto.*;
import com.lizandronarvaez.spring_ai.dto.GetCapitalRequest;
import com.lizandronarvaez.spring_ai.dto.Question;
import com.lizandronarvaez.spring_ai.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * Clase: OpenAIServiceImpl.java
 */

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {
    private static final Logger logger = LoggerFactory.getLogger(OpenAIServiceImpl.class);

    private final ChatModel chatModel;
    private final ObjectMapper objectMapper;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfoPrompt;

    @Override
    public MessageResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        Prompt prompt = createPrompt(getCapitalWithInfoPrompt, getCapitalRequest,null);
        ChatResponse response = chatModel.call(prompt);

        return new MessageResponse(response.getResult().getOutput().getText());
    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();
        Prompt prompt = createPrompt(getCapitalPrompt, getCapitalRequest, format);
        ChatResponse response = chatModel.call(prompt);

        System.out.println();
        System.out.println("format: " + format);
        System.out.println();
        System.out.println(response);
        System.out.println();
        System.out.println(response.getResult().getOutput().getText());

        return converter.convert(Objects.requireNonNull(response.getResult().getOutput().getText()));
    }

    @Override
    public String getAnswer(String question) {
        Prompt prompt = createPrompt(question);
        ChatResponse chatResponse = chatModel.call(prompt);
        return chatResponse.getResult().getOutput().getText();
    }

    @Override
    public MessageResponse getAnswer(Question question) {
        logger.info("Empezando la llamada a la IA con esta pregunta: {}", question);
        Prompt prompt = createPrompt(question);
        ChatResponse chatResponse = chatModel.call(prompt);
        logger.info("Terminando la llamada a la IA: {}", chatResponse);

        return new MessageResponse(chatResponse.getResult().getOutput().getText());
    }

    private Prompt createPrompt(Resource promptTemplateResource, GetCapitalRequest getCapitalRequest, String format) {
        PromptTemplate promptTemplate = new PromptTemplate(promptTemplateResource);
        return promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(), "format", format));
    }

    private Prompt createPrompt(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        return promptTemplate.create();
    }

    private Prompt createPrompt(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(String.valueOf(question));
        return promptTemplate.create();
    }
}
