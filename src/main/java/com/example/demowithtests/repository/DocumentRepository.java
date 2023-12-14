package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Document;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "INSERT INTO documents(number) VALUES (:number)", nativeQuery = true)
    void saveDocument(String number);

    Document findByIdByUserBeforeDeleting(Integer id);
}
