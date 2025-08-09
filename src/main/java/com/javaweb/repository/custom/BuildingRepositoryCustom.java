package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface BuildingRepositoryCustom {
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
    int countTotalItem();
    List<BuildingEntity> getAllBuilding(Pageable pageable);


}
