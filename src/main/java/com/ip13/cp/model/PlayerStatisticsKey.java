package com.ip13.cp.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatisticsKey implements Serializable {
    private int playerId;
    private int seasonId;
    private boolean isPo;

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && ((PlayerStatisticsKey) obj).playerId == this.playerId &&
                ((PlayerStatisticsKey) obj).seasonId == this.seasonId && ((PlayerStatisticsKey) obj).isPo == this.isPo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, seasonId, isPo);
    }
}
