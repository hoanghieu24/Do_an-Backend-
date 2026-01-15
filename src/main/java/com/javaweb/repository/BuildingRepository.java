package com.javaweb.repository;

import java.util.List;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//astract methotd
public interface BuildingRepository extends JpaRepository<BuildingEntity,Long> , BuildingRepositoryCustom {
    List<BuildingEntity> findByNameContaining(String name );
    List<BuildingEntity> findByNameContainingIgnoreCase(String name);
    List<BuildingEntity> findByIdIn(List<Long> ids);
    void deleteByIdIn(List<Long> ids);
    public  Object  findById(Long[] id);
//    List<BuildingEntity> findByIdIn(List<Long> ids);
@Query("""
SELECT DISTINCT b
FROM BuildingEntity b
JOIN b.users u
WHERE u.id = :staffId
AND (
    (:#{#builder.name} IS NULL OR b.name LIKE %:#{#builder.name}%)
)
""")
List<BuildingEntity> findByStaffAndCondition(
        @Param("staffId") Long staffId,
        @Param("builder") BuildingSearchBuilder builder
);
    @Query("""
SELECT COUNT(b) > 0
FROM BuildingEntity b
JOIN b.users u
WHERE b.id = :buildingId
AND u.id = :staffId
""")
    boolean isStaffOwner(Long staffId, Long buildingId);




}
