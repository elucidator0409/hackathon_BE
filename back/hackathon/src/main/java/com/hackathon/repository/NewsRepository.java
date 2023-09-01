package com.hackathon.repository;

import com.hackathon.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByActive(boolean active);

    List<News> findAllByActiveOrderByDateCreateDesc(boolean active);

}
