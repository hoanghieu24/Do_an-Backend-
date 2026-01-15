package com.javaweb.repository.custom.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.ContractEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.ContractDTO;
import com.javaweb.repository.ContractRepository;
import com.javaweb.repository.custom.ContractRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ContractRepositoryCustomImpl implements ContractRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ContractEntity> findByStaffId(Long staffId, ContractDTO builder) {
        StringBuilder sql = new StringBuilder("""
    SELECT c
    FROM ContractEntity c
    WHERE c.customer.id IN (
        SELECT ac.customer.id
        FROM AssignmentCustomerEntity ac
        WHERE ac.staff.id = :staffId
    )
    AND c.customer.isActive = 1
""");

        if (builder.getSellerName() != null) {
            sql.append(" AND c.customer.fullName LIKE :fullName");
        }
        if (builder.getSellerPhone() != null) {
            sql.append(" AND c.customer.phone LIKE :phone");
        }
        if (builder.getStatus() != null) {
            sql.append(" AND c.status = :status");  // chú ý: status ở ContractEntity
        }

        TypedQuery<ContractEntity> query = entityManager.createQuery(sql.toString(), ContractEntity.class);
        query.setParameter("staffId", staffId);

        if (builder.getSellerName() != null) {
            query.setParameter("fullName", "%" + builder.getSellerName() + "%");
        }
        if (builder.getSellerPhone() != null) {
            query.setParameter("phone", "%" + builder.getSellerPhone() + "%");
        }
        if (builder.getStatus() != null) {
            query.setParameter("status", builder.getStatus());
        }

        return query.getResultList();

    }
}

