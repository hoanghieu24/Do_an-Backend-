package com.javaweb.repository;

import com.javaweb.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {
    Optional<PasswordResetTokenEntity> findByToken(String token);
    void deleteByUser_Id(Long userId);
    Optional<PasswordResetTokenEntity> findByUser_EmailAndToken(String email, String token);


}
