package com.javaweb.repository;

import java.util.List;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


//astract methotd
public interface BuildingRepository extends JpaRepository<BuildingEntity,Long> , BuildingRepositoryCustom {
    List<BuildingEntity> findByNameContaining(String name );
    List<BuildingEntity> findByNameContainingIgnoreCase(String name);
    List<BuildingEntity> findByIdIn(List<Long> ids);
    void deleteByIdIn(List<Long> ids);
    public  Object  findById(Long[] id);
//    List<BuildingEntity> findByIdIn(List<Long> ids);


}
