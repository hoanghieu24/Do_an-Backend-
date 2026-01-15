package com.javaweb.model.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private String userId;      // để phân biệt từng user / session
    private String userMessage; // nội

    // Getter và Setter
    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
