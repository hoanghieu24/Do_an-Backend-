package com.javaweb.repository;

import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> , UserRepositoryCustom {
    UserEntity findOneByUserNameAndStatus(String name, int status);
    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status,
                                                                                                  Pageable pageable);
    List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);
    Page<UserEntity> findByStatusNot(int status, Pageable pageable);
    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status);
    long countByStatusNot(int status);
    UserEntity findOneByUserName(String userName);
    List<UserEntity> findByIdIn(List<Long> id);
    List<UserEntity> deleteAllByRolesIn(List<RoleEntity> roleRepositories);
    @Query("SELECT u FROM UserEntity u WHERE "
            + "(:userName IS NULL OR u.userName LIKE %:userName%) AND "
            + "(:fullName IS NULL OR u.fullName LIKE %:fullName%) AND "
            + "(:email IS NULL OR u.email LIKE %:email%) AND "
            + "u.status = 1")
    List<UserEntity> searchUsers(@Param("userName") String userName,
                                 @Param("fullName") String fullName,
                                 @Param("email") String email);


    Optional<UserEntity> findByEmail(String email);
}
