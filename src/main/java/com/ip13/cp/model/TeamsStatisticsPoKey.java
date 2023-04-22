package com.ip13.cp.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TeamsStatisticsPoKey implements Serializable {
    private int seasonId;
    private int homeTeamId;
    private int guestTeamId;
    private String stage;

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && ((TeamsStatisticsPoKey) obj).seasonId == this.seasonId &&
                ((TeamsStatisticsPoKey) obj).homeTeamId == this.homeTeamId &&
                ((TeamsStatisticsPoKey) obj).guestTeamId == this.guestTeamId &&
                ((TeamsStatisticsPoKey) obj).stage.equals(this.stage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonId, homeTeamId, guestTeamId, stage);
    }
}
