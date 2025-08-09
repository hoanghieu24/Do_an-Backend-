package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.MapUntil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT B.* FROM building AS B ");
        StringBuilder join = new StringBuilder("");
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryJoin(buildingSearchBuilder, join);
        queryNomal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        sql.append(join).append(where).append(" Group by B.id");
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem() {
        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }

    @Override
    public List<BuildingEntity> getAllBuilding(Pageable pageable) {
        StringBuilder sql = new StringBuilder(buildQueryFilter())
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql.toString());

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    // Kiểm tra câu Join
    public void queryJoin(BuildingSearchBuilder buildingSearchBuilder, StringBuilder join) {
        Integer staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            join.append(" JOIN assignmentbuilding AS ab ON B.id = ab.buildingid ");
        }
        Integer rentFromArea = buildingSearchBuilder.getAreaFrom();
        Integer rentToArea = buildingSearchBuilder.getAreaTo();
        if (rentFromArea != null  || rentToArea != null) {
            join.append(" JOIN rentarea ra ON B.id = ra.buildingid ");
        }
    }

    // Kiểm tra câu query đơn giản
    public void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();

                // Bỏ qua những field xử lý phức tạp
                if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentPrice") && !key.startsWith("area")) {
                    Object value = field.get(buildingSearchBuilder); // Lấy giá trị của field

                    if (value != null) { // Kiểm tra nếu không phải null
                        String trimmedValue =  value.toString().trim().replaceAll("\\s+", " ");// Loại bỏ khoảng trắng đầu/cuối

                        if (!trimmedValue.isEmpty()) { // Nếu giá trị không rỗng sau khi loại khoảng trắng
                            if (field.getType().getName().equals("java.lang.Integer") || field.getType().getName().equals("java.lang.Long")) {
                                where.append(" AND B." + key + " = " + trimmedValue);
                            } else {
                                where.append(" AND B." + key + " LIKE '%" + trimmedValue + "%'");
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // Kiểm tra câu query phức tạp
    public void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Integer staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" AND ab.staffId = " + staffId);
        }
        Integer rentFromArea = buildingSearchBuilder.getAreaFrom();
        Integer rentToArea = buildingSearchBuilder.getAreaTo();
        if (rentFromArea != null || rentToArea != null) {
            if (rentFromArea != null) {
                where.append(" AND ra.value >= " + rentFromArea);
            }
            if (rentToArea != null) {
                where.append(" AND ra.value <= " + rentToArea);
            }
        }
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentPriceFrom != null && !rentPriceFrom.equals("") || rentPriceTo != null) {
            if (rentPriceFrom != null) {
                where.append(" AND B.rentprice >= " + rentPriceFrom);
            }
            if (rentPriceTo != null) {
                where.append(" AND B.rentprice <= " + rentPriceTo);
            }
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();

        if (typeCode != null && !typeCode.isEmpty()) {

            List<buildingType> typeCodes = typeCode.stream()
                    .map(type -> buildingType.valueOf(type.trim()))
                    .collect(Collectors.toList());

            // Xây dựng chuỗi cho câu truy vấn SQL
            String typeCodeString = typeCodes.stream()
                    .map(Enum::name)  // Lấy tên của từng enum (chuỗi)
                    .map(type -> "'%" + type + "%'")
                    .collect(Collectors.joining(" OR B.type LIKE "));  // Tạo chuỗi cho câu truy vấn SQL

            // In ra chuỗi để kiểm tra
            System.out.println("Chuỗi typeCode cho câu truy vấn: " + typeCodeString);

            // Thêm điều kiện vào câu truy vấn SQL
            where.append(" AND (B.type LIKE " + typeCodeString + ")");
        }
    }
    private String buildQueryFilter() {
        String sql = "SELECT * FROM building b WHERE 1=1 ";
        return sql;
    }
    }
