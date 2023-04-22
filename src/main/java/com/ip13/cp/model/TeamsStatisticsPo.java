package com.ip13.cp.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TeamsStatisticsPo {
    @EmbeddedId
    private TeamsStatisticsPoKey teamStatisticsPOKey;
    private int homeTeamWins;
    private int guestTeamWins;
}
