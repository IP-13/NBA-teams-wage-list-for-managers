package com.ip13.cp.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TeamsStatisticsKey implements Serializable {
    private int teamId;
    private int seasonId;

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && ((TeamsStatisticsKey) obj).teamId == this.teamId &&
                ((TeamsStatisticsKey) obj).seasonId == this.seasonId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, seasonId);
    }
}
