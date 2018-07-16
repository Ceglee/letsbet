package com.letsbet.webservices.app.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries(
        @NamedQuery(
        name = "query_finished_games_with_no_result",
        query = "select g from Game as g where (g.teamAwayResult is null or g.teamHomeResult is null) and g.startDate < :now")
)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "block_bet_date")
    private LocalDateTime blockBetDate;
    private String title;
    private String description;

    @Column(name = "team_away_result")
    private Short teamAwayResult;

    @Column(name = "team_home_result")
    private Short teamHomeResult;

    @Column(name = "stage")
    private Short stage;

    @Column(name = "winner_team")
    private Short winnerTeam;

    @OneToMany(mappedBy = "game")
    private List<Bet> bets = new LinkedList<>();

    public Short getStage() {
        return stage;
    }

    public void setStage(Short stage) {
        this.stage = stage;
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

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
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
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(startDate, game.startDate) &&
                Objects.equals(blockBetDate, game.blockBetDate) &&
                Objects.equals(title, game.title) &&
                Objects.equals(description, game.description) &&
                Objects.equals(teamAwayResult, game.teamAwayResult) &&
                Objects.equals(teamHomeResult, game.teamHomeResult) &&
                Objects.equals(stage, game.stage) &&
                Objects.equals(winnerTeam, game.winnerTeam) &&
                Objects.equals(bets, game.bets);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, startDate, blockBetDate, title, description, teamAwayResult, teamHomeResult, stage, winnerTeam, bets);
    }
}
