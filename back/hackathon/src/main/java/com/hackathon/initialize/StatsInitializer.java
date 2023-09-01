package com.hackathon.initialize;

import com.hackathon.entity.Stats;
import com.hackathon.repository.StatsRepository;
import com.hackathon.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StatsInitializer {
    @Autowired
    private StatsService statsService;



    @EventListener(ApplicationReadyEvent.class)
    public void initializeStats() {
        if (statsService.getStats() == null) {
            statsService.createInitialStats();
        }
    }


}
