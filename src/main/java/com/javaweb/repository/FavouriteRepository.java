package com.javaweb.repository;

import com.javaweb.entity.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Long> {
    List<FavouriteEntity> findByUserId(Long UserId);

    boolean existsByUserIdAndBuildingId(Long UserId, Long buildingId);

    void deleteByUser_IdAndBuilding_Id(Long userId, Long buildingId);
}
