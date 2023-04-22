package com.ip13.cp.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class PlayerSalary {
    @EmbeddedId
    private PlayerSalaryKey playerSalaryKey;
    private int salary;
}
