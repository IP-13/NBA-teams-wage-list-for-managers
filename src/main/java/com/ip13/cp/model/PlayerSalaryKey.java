package com.ip13.cp.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayerSalaryKey implements Serializable {
    private int seasonId;
    private int playerId;
    private int teamId;

    public PlayerSalaryKey(int seasonId, int playerId, int teamId) {
        this.seasonId = seasonId;
        this.playerId = playerId;
        this.teamId = teamId;
    }

    public PlayerSalaryKey() {
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && ((PlayerSalaryKey) obj).seasonId == this.seasonId &&
                ((PlayerSalaryKey) obj).playerId == this.playerId && ((PlayerSalaryKey) obj).teamId == this.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonId, playerId, teamId);
    }
}
