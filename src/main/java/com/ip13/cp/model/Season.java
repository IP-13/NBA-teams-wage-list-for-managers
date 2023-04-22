package com.ip13.cp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Season {
    @Id
    private int id;
    private int year;
}
