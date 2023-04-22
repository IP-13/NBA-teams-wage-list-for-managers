package com.ip13.cp.controller;

import com.ip13.cp.model.TeamsStatisticsPo;
import com.ip13.cp.model.TeamsStatisticsPoKey;
import com.ip13.cp.repository.TeamStatisticsPoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamStatisticsPOController {
    @Autowired
    private TeamStatisticsPoRepository teamStatisticsPORepository;


    @GetMapping("/team_statistics_po/getAll")
    public Iterable<TeamsStatisticsPo> getAll() {
        return teamStatisticsPORepository.findAll();
    }


    @GetMapping("/team_statistics_po/get")
    public TeamsStatisticsPo get(@RequestParam int seasonId, @RequestParam int homeTeamId, @RequestParam int guestTeamId, @RequestParam String stage) {
        return teamStatisticsPORepository.findById(new TeamsStatisticsPoKey(seasonId, homeTeamId, guestTeamId, stage)).
                orElse(null);
    }


    @PostMapping("/team_statistics_po/add")
    public TeamsStatisticsPo add(@RequestBody TeamsStatisticsPo teamStatisticsPO) {
        return teamStatisticsPORepository.save(teamStatisticsPO);
    }


    @DeleteMapping("/team_statistics_po/delete")
    public void delete(@RequestParam int seasonId, @RequestBody int homeTeamId, @RequestParam int guestTeamId, @RequestParam String stage) {
        teamStatisticsPORepository.deleteById(new TeamsStatisticsPoKey(seasonId, homeTeamId, guestTeamId, stage));
    }
}
