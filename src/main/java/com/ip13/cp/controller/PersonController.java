package com.ip13.cp.controller;

import com.ip13.cp.model.Person;
import com.ip13.cp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/person/getAll")
    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }


    @GetMapping("/person/get/{id}")
    public Person get(@PathVariable Integer id) {
        return personRepository.findById(id).orElse(null);
    }


    @PostMapping("/person/add")
    public Person create(@RequestBody Person person) {
        return personRepository.save(person);
    }


    @DeleteMapping("/person/delete/{id}")
    public void delete(@PathVariable Integer id) {
        personRepository.deleteById(id);
    }
}
