package com.javaweb.service;

import com.javaweb.entity.PasswordResetTokenEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.PasswordResetTokenRepository;
import com.javaweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public String createPasswordResetToken(String email) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Email không tồn tại trong hệ thống.");
        }

        UserEntity user = userOpt.get();

        // Xoá token cũ (nếu có)
        tokenRepository.deleteByUser_Id(user.getId());

        // Tạo mã 6 chữ số
        int code = (int)(Math.random() * 900_000) + 100_000; // sẽ ra số từ 100000 -> 999999
        String token = String.valueOf(code);

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30); // Hết hạn sau 30 phút

        PasswordResetTokenEntity resetToken = PasswordResetTokenEntity.builder()
                .token(token)
                .user(user)
                .expirationTime(expirationTime)
                .build();

        tokenRepository.save(resetToken);

        // Log ra để kiểm tra
        System.out.println("🔹 Mã đặt lại mật khẩu cho " + email + ": " + token);

        // Trả về link giả để test (nếu muốn)
        return "http://localhost:8080/reset-password?token=" + token;
    }


    public boolean validateResetToken(String token) {
        Optional<PasswordResetTokenEntity> tokenOpt = tokenRepository.findByToken(token);
        return tokenOpt.isPresent() && tokenOpt.get().getExpirationTime().isAfter(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public boolean checkOtp(String email, String token) {
        Optional<PasswordResetTokenEntity> tokenEntity = tokenRepository.findByUser_EmailAndToken(email, token);
        if (tokenEntity.isEmpty()) return false;

        PasswordResetTokenEntity t = tokenEntity.get();

        if (t.isUsed()) return false;
        if (t.getExpirationTime().isBefore(LocalDateTime.now())) return false;

        return true;
    }



    @Transactional
    public void resetPasswordForEmailToken(String email, String token, String newPassword) {
        PasswordResetTokenEntity resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token không hợp lệ."));

        // kiểm tra token thuộc email và hợp lệ
        if (!resetToken.getUser().getEmail().equalsIgnoreCase(email)) {
            throw new IllegalArgumentException("Token không thuộc email này.");
        }
        if (resetToken.isUsed() || resetToken.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token đã dùng hoặc đã hết hạn.");
        }

        // hash mật khẩu trước khi lưu (rất quan trọng)
        String hashed = passwordEncoder.encode(newPassword);
        UserEntity user = resetToken.getUser();
        user.setPassword(hashed);
        userRepository.save(user);

        // đánh dấu token đã dùng hoặc xóa luôn
        resetToken.setUsed(true);
        tokenRepository.save(resetToken);
        // hoặc tokenRepository.delete(resetToken);
    }

}
