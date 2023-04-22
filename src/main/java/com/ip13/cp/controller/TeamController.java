package com.ip13.cp.controller;

import com.ip13.cp.model.Team;
import com.ip13.cp.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;


    @GetMapping("/team/getAll")
    public Iterable<Team> getAll() {
        return teamRepository.findAll();
    }


    @GetMapping("/team/get/{id}")
    public Team get(@PathVariable Integer id) {
        return teamRepository.findById(id).orElse(null);
    }


    @PostMapping("/team/add")
    public Team add(@RequestBody Team team) {
        return teamRepository.save(team);
    }


    @DeleteMapping("/team/delete/{id}")
    public void delete(@PathVariable Integer id) {
        teamRepository.deleteById(id);
    }
}
