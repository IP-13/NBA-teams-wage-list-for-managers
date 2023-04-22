package com.ip13.cp.controller;

import com.ip13.cp.model.PlayerSalary;
import com.ip13.cp.model.PlayerSalaryKey;
import com.ip13.cp.repository.PlayerSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerSalaryController {
    @Autowired
    private PlayerSalaryRepository playerSalaryRepository;


    @GetMapping("/player_salary/getAll")
    public Iterable<PlayerSalary> getAll() {
        return playerSalaryRepository.findAll();
    }


    @GetMapping("/player_salary/get/{seasonId}_{playerId}_{teamId}")
    public PlayerSalary get(@PathVariable Integer seasonId, @PathVariable Integer playerId, @PathVariable Integer teamId) {
        return playerSalaryRepository.findById(new PlayerSalaryKey(seasonId, playerId, teamId)).orElse(null);
    }


    @PostMapping("/player_salary/add")
    public PlayerSalary create(@RequestBody PlayerSalary playerSalary) {
        return playerSalaryRepository.save(playerSalary);
    }


    @DeleteMapping("/player_salary/delete/{seasonId}_{playerId}_{teamId}")
    public void delete(@PathVariable Integer seasonId, @PathVariable Integer playerId, @PathVariable Integer teamId) {
        playerSalaryRepository.deleteById(new PlayerSalaryKey(seasonId, playerId, teamId));
    }
}
