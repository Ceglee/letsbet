package com.letsbet.webservices.app.model.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.entities.Bet;
import com.letsbet.webservices.app.views.BetViews;

import java.util.Objects;

public class BetResource {

    @JsonView({BetViews.Details.class, BetViews.Overview.class})
    private Long id;

    @JsonView({BetViews.Details.class, BetViews.Overview.class})
    private Long userId;

    @JsonView({BetViews.Details.class, BetViews.Overview.class, BetViews.Create.class})
    private Long gameId;

    @JsonView({BetViews.Details.class, BetViews.Overview.class, BetViews.Create.class})
    private Long leagueId;

    @JsonView({BetViews.Details.class, BetViews.Overview.class, BetViews.Create.class})
    private Short homeScore;

    @JsonView({BetViews.Details.class, BetViews.Overview.class, BetViews.Create.class})
    private Short awayScore;

    @JsonView({BetViews.Details.class, BetViews.Overview.class, BetViews.Create.class})
    private Short winnerTeam;

    private Boolean withResult;

    @JsonView({BetViews.Details.class, BetViews.Overview.class})
    private Short points;

    public BetResource() {
    }

    public BetResource(Bet bet) {
        if (bet != null) {
            id = bet.getId();
            userId = bet.getUser() != null ? bet.getUser().getId() : null;
            gameId = bet.getGame() != null ? bet.getGame().getId() : null;
            leagueId = bet.getLeague() != null ? bet.getLeague().getId() : null;
            homeScore = bet.getHomeScore();
            awayScore = bet.getAwayScore();
            withResult = bet.getWithResult();
            winnerTeam = bet.getWinnerTeam();
            points = bet.getPoints();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public Short getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Short homeScore) {
        this.homeScore = homeScore;
    }

    public Short getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Short awayScore) {
        this.awayScore = awayScore;
    }

    public Boolean getWithResult() {
        return withResult;
    }

    public void setWithResult(Boolean withResult) {
        this.withResult = withResult;
    }

    public Short getPoints() {
        return points;
    }

    public void setPoints(Short points) {
        this.points = points;
    }

    public Short getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Short winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetResource that = (BetResource) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(gameId, that.gameId) &&
                Objects.equals(leagueId, that.leagueId) &&
                Objects.equals(homeScore, that.homeScore) &&
                Objects.equals(awayScore, that.awayScore) &&
                Objects.equals(winnerTeam, that.winnerTeam) &&
                Objects.equals(withResult, that.withResult) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, gameId, leagueId, homeScore, awayScore, winnerTeam, withResult, points);
    }
}
