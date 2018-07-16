package com.letsbet.webservices.app.model.resources;

import com.letsbet.webservices.app.model.entities.Bet;

public class ScoreResource {

    private Long userId;
    private Integer score;

    public ScoreResource() {
    }

    public ScoreResource(Bet bet) {
        if (bet != null) {
            this.userId = bet.getUser().getId();
            this.score = (int) bet.getPoints();
        }
    }

    public ScoreResource(Long userId, Integer score) {
        this.userId = userId;
        this.score = score;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
