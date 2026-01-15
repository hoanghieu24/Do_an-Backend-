package com.javaweb.repository;

import com.javaweb.entity.SellerRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRequestRepository extends JpaRepository<SellerRequestEntity,Long> {
    List<SellerRequestEntity> findByStatus(String status);

    boolean existsByUser_IdAndStatus(Long userId, String status);
}
