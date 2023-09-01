package com.hackathon.service;

import com.hackathon.entity.Stats;
import com.hackathon.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class StatsService {


    @Autowired
    private StatsRepository statsRepository;

    public Stats getStats() {
        List<Stats> statsList = statsRepository.findAll();
        if (!statsList.isEmpty()) {
            return statsList.get(0);
        }
        return null;
    }


    public void updateStats(int registerCount, int onlinePassCount, int offlinePassCount, int hackerthonCount) {
        Optional<Stats> existingStatsOptional = Optional.ofNullable(getStats());
        Stats newStats = new Stats();
        newStats.setRegisterCount(registerCount);
        newStats.setOnlinePassCount(onlinePassCount);
        newStats.setOfflinePassCount(offlinePassCount);
        newStats.setHackerthonCount(hackerthonCount);
        System.out.println("Before deletion and saving");

        if (existingStatsOptional.isPresent()) {
            System.out.println("Deleting existing stats: "+existingStatsOptional);

            Stats existingStats = existingStatsOptional.get();
            statsRepository.delete(existingStats); // Delete the old stats entity
        }
        System.out.println("Saving new stats");

        statsRepository.save(newStats); // Save the new stats entity
    }

    public Stats createInitialStats() {
        Stats initialStats = new Stats();
        initialStats.setRegisterCount(127);
        initialStats.setOnlinePassCount(1505);
        initialStats.setOfflinePassCount(109);
        initialStats.setHackerthonCount(102);
        return statsRepository.save(initialStats);
    }


}
