package com.javaweb.repository;

import java.util.List;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentareaRepository extends JpaRepository<RentareaEntity, Long> {

    List<RentareaEntity> deleteAllByBuilding(BuildingEntity buildingEntity);
    List<RentareaEntity> deleteAllByBuildingIn(List<BuildingEntity> buildingEntity);

}
