package com.letsbet.webservices.app.model.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.views.GameViews;
import com.letsbet.webservices.app.views.LeagueViews;

import java.time.LocalDateTime;
import java.util.Objects;

public class GameResource {

    @JsonView({LeagueViews.Details.class, LeagueViews.Update.class})
    private Long id;

    @JsonView(GameViews.Details.class)
    private LocalDateTime startDate;

    @JsonView(GameViews.Details.class)
    private LocalDateTime blockBetDate;

    @JsonView(GameViews.Details.class)
    private String title;

    @JsonView(GameViews.Details.class)
    private String description;

    @JsonView(GameViews.Details.class)
    private Short teamAwayResult;

    @JsonView(GameViews.Details.class)
    private Short teamHomeResult;

    @JsonView(GameViews.Details.class)
    private Short stage;

    @JsonView(GameViews.Details.class)
    private Short winnerTeam;

    public GameResource() {
    }

    public GameResource(Game game) {
        if (game != null) {
            id = game.getId();
            startDate = game.getStartDate();
            blockBetDate = game.getStartDate();
            title = game.getTitle();
            description = game.getDescription();
            teamAwayResult = game.getTeamAwayResult();
            teamHomeResult = game.getTeamHomeResult();
            stage = game.getStage();
            winnerTeam = game.getWinnerTeam();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getBlockBetDate() {
        return blockBetDate;
    }

    public void setBlockBetDate(LocalDateTime blockBetDate) {
        this.blockBetDate = blockBetDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getTeamAwayResult() {
        return teamAwayResult;
    }

    public void setTeamAwayResult(Short teamAwayResult) {
        this.teamAwayResult = teamAwayResult;
    }

    public Short getTeamHomeResult() {
        return teamHomeResult;
    }

    public void setTeamHomeResult(Short teamHomeResult) {
        this.teamHomeResult = teamHomeResult;
    }

    public Short getStage() {
        return stage;
    }

    public void setStage(Short stage) {
        this.stage = stage;
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
        GameResource that = (GameResource) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(blockBetDate, that.blockBetDate) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(teamAwayResult, that.teamAwayResult) &&
                Objects.equals(teamHomeResult, that.teamHomeResult) &&
                Objects.equals(stage, that.stage) &&
                Objects.equals(winnerTeam, that.winnerTeam);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, startDate, blockBetDate, title, description, teamAwayResult, teamHomeResult, stage, winnerTeam);
    }
}
