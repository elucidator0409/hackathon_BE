package com.hackathon.repository;

import com.hackathon.entity.MockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MockDataRepository extends JpaRepository<MockData, Long>{
    @Query("SELECT m.score FROM MockData m WHERE m.email = :email AND m.type = :type")
    List<String> findResultsByEmail(String email, String type);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mock_data", nativeQuery = true)
    void deleteAllMockData();

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE mock_data AUTO_INCREMENT = 1", nativeQuery = true)
    void resetMockDataId();
}
