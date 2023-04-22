package com.ip13.cp.controller;

import com.ip13.cp.model.Season;
import com.ip13.cp.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeasonController {
    @Autowired
    private SeasonRepository seasonRepository;


    @GetMapping("/season/getAll")
    public Iterable<Season> getAll() {
        return seasonRepository.findAll();
    }


    @GetMapping("/season/get/{id}")
    public Season get(@PathVariable Integer id) {
        return seasonRepository.findById(id).orElse(null);
    }


    @PostMapping("/season/add")
    public Season add(@RequestBody Season season) {
        return seasonRepository.save(season);
    }


    @DeleteMapping("/season/delete/{id}")
    public void delete(@PathVariable Integer id) {
        seasonRepository.deleteById(id);
    }
}
