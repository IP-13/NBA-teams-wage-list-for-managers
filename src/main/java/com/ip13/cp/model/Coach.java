package com.ip13.cp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Coach {
    @Id
    private int id;
    private int personId;
    private int teamId;
}
