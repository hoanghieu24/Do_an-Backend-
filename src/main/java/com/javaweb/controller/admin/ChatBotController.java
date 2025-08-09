package com.javaweb.controller.admin;

import com.javaweb.model.dto.ChatRequest;
import com.javaweb.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    // API gửi câu hỏi tới ChatGPT và nhận câu trả lời
    @PostMapping("/openai")
    public ResponseEntity<String> getChatGPTResponse(@RequestBody ChatRequest chatRequest) {
        String userMessage = chatRequest.getUserMessage();
        String chatGptResponse = chatBotService.getGeminiResponse(userMessage);
        return ResponseEntity.ok(chatGptResponse);
    }
}
