package com.javaweb.repository;

import com.javaweb.entity.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Long> {
    List<FavouriteEntity> findByCustomerId(Long customerId);

    boolean existsByCustomerIdAndBuildingId(Long customerId, Long buildingId);

    void deleteByCustomerIdAndBuildingId(Long customerId, Long buildingId);
}
