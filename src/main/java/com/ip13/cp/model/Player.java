package com.ip13.cp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Player {
    @Id
    private int id;
    private int personId;
    private int teamId;
}
