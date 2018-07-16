package com.letsbet.webservices.app.model.entities;

import com.letsbet.webservices.app.model.resources.BetResource;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name = "get_user_bets",
                query = "select b from Bet as b inner join b.user as u where u.uid = :uid"
        ),
        @NamedQuery(
                name = "get_bets_for_league",
                query = "select b from Bet as b inner join b.league as l where l.id = :id"
        ),
        @NamedQuery(
                name = "get_user_bets_for_league",
                query = "select b from Bet as b inner join b.user as u inner join b.league as l where u.uid = :uid and l.id = :id"
        ),
        @NamedQuery(
                name = "get_other_user_bets_for_league",
                query = "select b from Bet as b inner join b.user as u inner join b.league as l where u.id = :userId and l.id = :id"
        )
})
@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    @Column(name = "home_score")
    private Short homeScore;

    @Column(name = "away_score")
    private Short awayScore;

    @Column(name = "winner_team")
    private Short winnerTeam;

    @Column(name = "with_result")
    private Boolean withResult;
    private Short points;

    public Bet() {
    }

    public Bet(BetResource resource) {
        if (resource != null) {
            this.homeScore = resource.getHomeScore();
            this.awayScore = resource.getAwayScore();
            this.withResult = false;
            this.points = 0;
            this.winnerTeam = resource.getWinnerTeam();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
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
        Bet bet = (Bet) o;
        return Objects.equals(id, bet.id) &&
                Objects.equals(user, bet.user) &&
                Objects.equals(game, bet.game) &&
                Objects.equals(league, bet.league) &&
                Objects.equals(homeScore, bet.homeScore) &&
                Objects.equals(awayScore, bet.awayScore) &&
                Objects.equals(winnerTeam, bet.winnerTeam) &&
                Objects.equals(withResult, bet.withResult) &&
                Objects.equals(points, bet.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, game, league, homeScore, awayScore, winnerTeam, withResult, points);
    }
}
