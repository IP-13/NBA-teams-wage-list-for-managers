package com.ip13.cp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Person {
    @Id
    private int id;
    private String name;
    private String surname;
    private LocalDate date_of_birth;
    private int height;
    private int weight;
}
