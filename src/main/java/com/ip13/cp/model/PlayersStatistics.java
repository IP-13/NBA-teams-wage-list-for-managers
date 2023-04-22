package com.ip13.cp.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class PlayersStatistics {
    @EmbeddedId
    private PlayerStatisticsKey playerStatisticsKey;
    private float mpg;
    private float ppg;
    private float rpg;
    private float apg;
    private float bpg;
    private float spg;
    private int gp;
}

