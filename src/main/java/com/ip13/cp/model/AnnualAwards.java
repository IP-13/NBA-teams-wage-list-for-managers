package com.ip13.cp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AnnualAwards {
    @Id
    private int id;
    private int seasonId;
    private int mvp;
    private int defensivePlayerOfTheYear;
    private int rookieOfTheYear;
    private int coachOfTheYear;
    private int sixPlayerOfTheYear;
}
