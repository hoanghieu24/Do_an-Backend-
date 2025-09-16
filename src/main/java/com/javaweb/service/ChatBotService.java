package com.javaweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.controller.admin.BuildingController;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.javaweb.model.response.BuildingResponse;

import java.util.*;

@Service
public class ChatBotService {
    private final RestTemplate restTemplate;

    @Autowired
    private BuildingService buildingService; // dùng service, không gọi controller

    @Value("${google.ai.key}")
    private String googleApiKey;

    public ChatBotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGeminiResponse(String userMessage) {
        try {
            String MODEL_NAME = "gemini-2.0-flash";
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/"
                    + MODEL_NAME + ":generateContent?key=" + googleApiKey;

            // Lấy danh sách tòa nhà từ service
            List<BuildingResponse> buildings = buildingService.findAll(new HashMap<>(), List.of(new String[]{}));

//            // Convert danh sách tòa nhà thành String mô tả
//            StringBuilder buildingInfo = new StringBuilder();
//            for (BuildingResponse b : buildings) {
//                buildingInfo.append(b.getNameBuilding())
//                        .append(" - Địa chỉ: ").append(b.getAddress())
//                        .append(" - Giá thuê: ").append(b.getRentPrice())
//                        .append("\n");
//            }


            String baseMessage = "Bạn là HiderAI, một chuyên gia bất động sản chuyên nghiệp, thân thiện. " +
                    "Hãy trả lời câu hỏi với tư cách là người bạn am hiểu về bất động sản. " +
                    "        - Giới hạn 2-3 câu ngắn gọn\n" +
                    "        - Tập trung vào toà nhà và bất động sản \n" +
                    "        - Đưa ra lời khuyên cụ thể\n" +
                    "        - Kết thúc bằng câu động viên ngắn";


            String finalMessage = baseMessage + "\nNgười dùng: " + userMessage;


            Map<String, Object> request = Map.of(
                    "contents", List.of(Map.of(
                            "parts", List.of(Map.of("text", finalMessage))
                    ))
            );

            System.out.println("câu tr lời là : " + finalMessage);

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
