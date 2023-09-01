package com.hackathon.repository;

import com.hackathon.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<Stats, Integer> {
}