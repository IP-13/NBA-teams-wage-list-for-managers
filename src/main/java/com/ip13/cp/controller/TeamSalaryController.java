package com.ip13.cp.controller;

import com.ip13.cp.model.TeamSalary;
import com.ip13.cp.model.TeamSalaryKey;
import com.ip13.cp.repository.TeamSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamSalaryController {
    @Autowired
    private TeamSalaryRepository teamSalaryRepository;


    @GetMapping("/team_salary/getAll")
    public Iterable<TeamSalary> getAll() {
        return teamSalaryRepository.findAll();
    }


    @GetMapping("/team_salary/get")
    public TeamSalary get(@RequestParam Integer teamId, @RequestParam int seasonId) {
        return teamSalaryRepository.findById(new TeamSalaryKey(teamId, seasonId)).orElse(null);
    }


    @PostMapping("/team_salary/add")
    public TeamSalary add(@RequestBody TeamSalary teamSalary) {
        return teamSalaryRepository.save(teamSalary);
    }


    @DeleteMapping("/team_salary/delete")
    public void delete(@RequestParam int teamId, @RequestParam int seasonId) {
        teamSalaryRepository.deleteById(new TeamSalaryKey(teamId, seasonId));
    }
}
