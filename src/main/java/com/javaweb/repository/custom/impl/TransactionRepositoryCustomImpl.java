package com.javaweb.repository.custom.impl;

import com.javaweb.builder.TransationSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.custom.TransactionRepositoryCustom;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;


public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TransactionEntity> findAll(TransationSearchBuilder transationSearchBuilder, Long id) {
        StringBuilder sql = new StringBuilder("SELECT t.* FROM transaction AS t WHERE 1=1 ");

        if (id != null) {
            sql.append(" AND t.customerid = ").append(id);
        }
        sql.append(" AND t.code = 'CSKH' ");


        Query query = entityManager.createNativeQuery(sql.toString(), TransactionEntity.class);
        return query.getResultList();
    }

    @Override
    public List<TransactionEntity> findAlls(TransationSearchBuilder transationSearchBuilder, Long id) {
        StringBuilder sql = new StringBuilder("SELECT t.* FROM transaction AS t WHERE 1=1 ");

        if (id != null) {
            sql.append(" AND t.customerid = ").append(id);
        }
        sql.append(" AND t.code = 'DDX' ");


        Query query = entityManager.createNativeQuery(sql.toString(), TransactionEntity.class);
        return query.getResultList();
    }

    // Đếm tổng số item trong bảng customer
    @Override
    public int countTotalItem() {
        String sql = "SELECT COUNT(*) FROM customer WHERE is_active=1";
        Query query = entityManager.createNativeQuery(sql);
        return ((Number) query.getSingleResult()).intValue(); // Chuyển đổi kết quả sang int
    }

    // Lấy danh sách customer có phân trang
    @Override
    public List<TransactionEntity> getAllCustomer(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM transaction WHERE 1=1 ")
                .append("LIMIT ").append(pageable.getPageSize()).append(" ")
                .append("OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql);

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }
}
