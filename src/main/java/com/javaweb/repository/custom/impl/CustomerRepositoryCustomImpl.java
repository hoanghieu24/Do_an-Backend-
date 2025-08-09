package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CustomerEntity> findAll(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT C.* FROM customer AS C ");
        StringBuilder join = new StringBuilder("");
        StringBuilder where = new StringBuilder(" WHERE 1=1 AND C.is_active = 1 ");
        queryJoin(customerSearchBuilder, join);
        queryNomal(customerSearchBuilder, where);
        querySpecial(customerSearchBuilder, where);
        sql.append(join).append(where).append(" Group by C.id");
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);


        return query.getResultList();
    }

    // Kiểm tra câu Join
    public void queryJoin(CustomerSearchBuilder customerSearchBuilder, StringBuilder join) {
        Integer staffId = customerSearchBuilder.getStaffId();
        if (staffId != null) {
            join.append(" JOIN assignmentcustomer AS a ON  c.id = a.customerid  ");
        }
    }

    public void queryNomal(CustomerSearchBuilder customerSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();

                // Bỏ qua những field xử lý phức tạp
                if (!key.equals("staffId")) {
                    Object value = field.get(customerSearchBuilder);

                    if (value != null) { // Kiểm tra nếu không phải null
                        String trimmedValue = value.toString().trim().replaceAll("\\s+", " "); // Loại bỏ khoảng trắng đầu/cuối

                        if (!trimmedValue.isEmpty() ) { // Nếu giá trị hợp lệ
                            if (field.getType().getName().equals("java.lang.Integer") || field.getType().getName().equals("java.lang.Long")) {
                                where.append(" AND C." + key + " = " + trimmedValue);
                            } else {
                                where.append(" AND C." + key + " LIKE '%" + trimmedValue + "%'");
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
    public void querySpecial(CustomerSearchBuilder customerSearchBuilder, StringBuilder where) {
        Integer staffId = customerSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" AND a.staffId = " + staffId);
        }
    }

    @Override
    public int countTotalItem() {
        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }

    @Override
    public List<CustomerEntity> getAllCustomer(Pageable pageable) {
        StringBuilder sql = new StringBuilder(buildQueryFilter())
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql.toString());

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }


    private String buildQueryFilter() {
        String sql = "SELECT * FROM customer as c WHERE c.is_active=1 ";
        return sql;
    }
}
