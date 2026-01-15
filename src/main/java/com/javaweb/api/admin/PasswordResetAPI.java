package com.javaweb.api.admin;

import com.javaweb.service.PasswordResetService;
import javax.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordResetAPI {

    private final PasswordResetService passwordResetService;

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) throws MessagingException {
        passwordResetService.createPasswordResetToken(email);
        return ResponseEntity.ok("Đã gửi email đặt lại mật khẩu.");
    }

    // Check OTP - chỉ kiểm tra, không đánh dấu used
    @GetMapping("/checkOTP")
    public ResponseEntity<?> checkOtp(@RequestParam String email, @RequestParam String token) {
        boolean valid = passwordResetService.checkOtp(email, token);
        if (valid) {
            return ResponseEntity.ok(Map.of("valid", true, "message", "Mã OTP đúng."));
        } else {
            return ResponseEntity.status(400).body(Map.of("valid", false, "message", "Mã OTP sai hoặc đã hết hạn."));
        }
    }


    // Reset password - bắt buộc gửi email + token + newPassword
    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam String email,
                                           @RequestParam String token,
                                           @RequestParam String newPassword) {
        passwordResetService.resetPasswordForEmailToken(email, token, newPassword);
        return ResponseEntity.ok(Map.of("message", "Đặt lại mật khẩu thành công."));
    }


    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        boolean valid = passwordResetService.validateResetToken(token);
        return ResponseEntity.ok(valid ? "Token hợp lệ." : "Token không hợp lệ hoặc đã hết hạn.");
    }
}
