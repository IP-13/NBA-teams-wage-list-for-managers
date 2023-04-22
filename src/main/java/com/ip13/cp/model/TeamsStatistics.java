package com.ip13.cp.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TeamsStatistics {
    @EmbeddedId
    private TeamsStatisticsKey teamStatisticsKey;
    private int wins;
    private int loses;
}
