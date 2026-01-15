package com.javaweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.model.response.BuildingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class ChatBotService {

    private final RestTemplate restTemplate;

    @Autowired
    private BuildingService buildingService;

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.model}")
    private String model;

    public ChatBotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAIResponse(String userMessage, HttpSession session) {
        try {
            String apiUrl = "https://openrouter.ai/api/v1/chat/completions";

            // 🧠 Lấy lịch sử hội thoại
            List<Map<String, String>> history =
                    (List<Map<String, String>>) session.getAttribute("chatHistory");
            if (history == null) {
                history = new ArrayList<>();
                session.setAttribute("chatHistory", history);
            }

            // 🏢 Load dữ liệu tòa nhà
            List<BuildingResponse> buildings = buildingService.findAll(Map.of(), List.of());
            StringBuilder buildingInfo = new StringBuilder();
            for (BuildingResponse b : buildings) {
                buildingInfo.append(b.getNameBuilding())
                        .append(" - ").append(b.getAddress())
                        .append(" - Giá thuê: ").append(b.getRentPrice())
                        .append("\n");
            }

            // 🧠 System prompt
            List<Map<String, String>> messages = new ArrayList<>();

            messages.add(Map.of(
                    "role", "system",
                    "content", "Bạn là HiderAI – chuyên gia tư vấn bất động sản.\n"
                            + "Danh sách tòa nhà:\n" + buildingInfo
            ));

            // 🧠 Lịch sử chat
            for (Map<String, String> msg : history) {
                messages.add(Map.of(
                        "role", msg.get("role"),
                        "content", msg.get("text")
                ));
            }

            // 🧍 Tin nhắn mới
            messages.add(Map.of("role", "user", "content", userMessage));

            Map<String, Object> request = Map.of(
                    "model", model,
                    "messages", messages,
                    "temperature", 0.7
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("HTTP-Referer", "http://localhost");
            headers.set("X-Title", "RealEstateAI");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String botReply = root
                    .path("choices").get(0)
                    .path("message")
                    .path("content")
                    .asText();

            // 💾 Lưu lịch sử
            history.add(Map.of("role", "user", "text", userMessage));
            history.add(Map.of("role", "assistant", "text", botReply));

            return botReply;

        } catch (Exception e) {
            e.printStackTrace();
            return "AI lỗi: " + e.getMessage();
        }
    }
}
