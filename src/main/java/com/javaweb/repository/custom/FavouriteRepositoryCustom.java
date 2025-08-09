package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.FavouriteEntity;

import java.util.List;

public interface FavouriteRepositoryCustom {
    public List<FavouriteEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
