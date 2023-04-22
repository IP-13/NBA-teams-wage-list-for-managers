package com.ip13.cp.controller;

import com.ip13.cp.model.TeamsStatistics;
import com.ip13.cp.model.TeamsStatisticsKey;
import com.ip13.cp.repository.TeamStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamStatisticsController {
    @Autowired
    private TeamStatisticsRepository teamStatisticsRepository;


    @GetMapping("/team_statistics/getAll")
    public Iterable<TeamsStatistics> getAll() {
        return teamStatisticsRepository.findAll();
    }


    @GetMapping("/team_statistics/get")
    public TeamsStatistics get(@RequestParam int teamId, @RequestParam int seasonId) {
        return teamStatisticsRepository.findById(new TeamsStatisticsKey(teamId, seasonId)).orElse(null);
    }


    @PostMapping("/team_statistics/add")
    public TeamsStatistics add(@RequestBody TeamsStatistics teamStatistics) {
        return teamStatisticsRepository.save(teamStatistics);
    }


    @DeleteMapping("/team_statistics/delete")
    public void delete(@RequestParam int teamId, @RequestParam int seasonId) {
        teamStatisticsRepository.deleteById(new TeamsStatisticsKey(teamId, seasonId));
    }

}
