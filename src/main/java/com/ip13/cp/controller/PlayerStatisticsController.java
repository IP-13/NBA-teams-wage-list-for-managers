package com.ip13.cp.controller;

import com.ip13.cp.model.PlayersStatistics;
import com.ip13.cp.model.PlayerStatisticsKey;
import com.ip13.cp.repository.PlayerStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerStatisticsController {
    @Autowired
    private PlayerStatisticsRepository playerStatisticsRepository;


    @GetMapping("/players_statistics/getAll")
    public Iterable<PlayersStatistics> getAll() {
        return playerStatisticsRepository.findAll();
    }


    @GetMapping("/players_statistics/get/{playerId}_{seasonId}_{isPO}")
    public PlayersStatistics get(@PathVariable Integer playerId, @PathVariable Integer seasonId, @PathVariable Boolean isPO) {
        return playerStatisticsRepository.findById(new PlayerStatisticsKey(playerId, seasonId, isPO)).orElse(null);
    }


    @PostMapping("/players_statistics/add")
    public PlayersStatistics add(@RequestBody PlayersStatistics playerStatistics) {
        return playerStatisticsRepository.save(playerStatistics);
    }


    @DeleteMapping("/players_statistics/delete")
    public void delete(@RequestParam Integer playerId, @RequestParam Integer seasonId, @RequestParam Boolean isPO) {
        playerStatisticsRepository.deleteById(new PlayerStatisticsKey(playerId, seasonId, isPO));
    }
}
