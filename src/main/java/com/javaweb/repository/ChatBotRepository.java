package com.javaweb.repository;

import com.javaweb.entity.ChatBotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatBotRepository extends JpaRepository<ChatBotEntity, Long> {

    List<ChatBotEntity> findByCustomerId(Long customerId);


    List<ChatBotEntity> findByQuestionContainingIgnoreCase(String keyword);

}
