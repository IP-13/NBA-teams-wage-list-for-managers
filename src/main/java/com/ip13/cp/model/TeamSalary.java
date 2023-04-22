package com.ip13.cp.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class TeamSalary {
    @EmbeddedId
    private TeamSalaryKey teamSalaryKey;
    private int totalSalary;
}
