package com.javaweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatBotService {
    private final RestTemplate restTemplate;

    @Autowired
    private BuildingRepository buildingRepository; //

    @Value("${google.ai.key}")
    private String googleApiKey;

    public ChatBotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Giữ nguyên phương thức gốc
    public String getGeminiResponse(String userMessage) {
        try {
            String MODEL_NAME = "gemini-2.0-flash";
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/"
                    + MODEL_NAME + ":generateContent?key=" + googleApiKey;

            Map<String, Object> request = Map.of(
                    "contents", List.of(Map.of(
                            "parts", List.of(Map.of("text", userMessage))
                    )));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            if (root.has("candidates") && root.get("candidates").size() > 0) {
                return root.path("candidates").get(0)
                        .path("content").path("parts").get(0)
                        .path("text").asText();
            } else {
                return "Error: No response from Gemini. Full API response: " + root.toString();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }







}