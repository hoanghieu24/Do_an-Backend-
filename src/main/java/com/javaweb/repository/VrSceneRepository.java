package com.javaweb.repository;

import com.javaweb.entity.VrSceneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VrSceneRepository extends JpaRepository<VrSceneEntity, Long> {
    List<VrSceneEntity> findByBuildingId(Long buildingId);
}
