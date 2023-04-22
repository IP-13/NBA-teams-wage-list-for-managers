package com.ip13.cp.controller;

import com.ip13.cp.model.Player;
import com.ip13.cp.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;


    @GetMapping("/player/getAll")
    public Iterable<Player> getAll() {
        return playerRepository.findAll();
    }


    @GetMapping("/player/get/{id}")
    public Player get(@PathVariable Integer id) {
        return playerRepository.findById(id).orElse(null);
    }


    @PostMapping("/player/add")
    public Player add(@RequestBody Player player) {
        return playerRepository.save(player);
    }


    @DeleteMapping("/player/delete/{id}")
    public void delete(@PathVariable Integer id) {
        playerRepository.deleteById(id);
    }
}
