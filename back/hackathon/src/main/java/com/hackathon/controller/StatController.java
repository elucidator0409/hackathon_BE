package com.hackathon.controller;


import com.hackathon.entity.Stats;
import com.hackathon.service.StatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/stats")
public class StatController {
    @Autowired
    private StatsService statsService;

    @GetMapping
    public Stats getStats() {
        return statsService.getStats();
    }
}
