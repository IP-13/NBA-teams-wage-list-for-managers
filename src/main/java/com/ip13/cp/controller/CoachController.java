package com.ip13.cp.controller;

import com.ip13.cp.model.Coach;
import com.ip13.cp.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoachController {
    @Autowired
    private CoachRepository coachRepository;


    @GetMapping("/coach/getAll")
    public Iterable<Coach> getAll() {
        return coachRepository.findAll();
    }


    @GetMapping("/coach/get/{id}")
    public Coach get(@PathVariable Integer id) {
        return coachRepository.findById(id).orElse(null);
    }


    @PostMapping("/coach/add")
    public Coach create(@RequestBody Coach coach) {
        return coachRepository.save(coach);
    }


    @DeleteMapping("/coach/delete/{id}")
    public void delete(@PathVariable Integer id) {
        coachRepository.deleteById(id);
    }
}
