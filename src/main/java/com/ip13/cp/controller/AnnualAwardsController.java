package com.ip13.cp.controller;

import com.ip13.cp.model.AnnualAwards;
import com.ip13.cp.repository.AnnualAwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnnualAwardsController {
    @Autowired
    private AnnualAwardsRepository annualAwardsRepository;


    @GetMapping("/annual_awards/getAll")
    public Iterable<AnnualAwards> getAll() {
        return annualAwardsRepository.findAll();
    }


    @GetMapping("/annual_awards/get/{id}")
    public AnnualAwards get(@PathVariable Integer id) {
        return annualAwardsRepository.findById(id).orElse(null);
    }


    @PostMapping("/annual_awards/add")
    public AnnualAwards create(@RequestBody AnnualAwards annualAwards) {
        return annualAwardsRepository.save(annualAwards);
    }


    @DeleteMapping("/annual_awards/delete/{id}")
    public void delete(@PathVariable Integer id) {
        annualAwardsRepository.deleteById(id);
    }
}
