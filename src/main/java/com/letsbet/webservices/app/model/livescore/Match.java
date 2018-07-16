package com.letsbet.webservices.app.model.livescore;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Match {

    private String id;
    private String date;

    @JsonProperty("home_name")
    private String homeName;

    @JsonProperty("away_name")
    private String awayName;
    private String score;

    @JsonProperty("ht_score")
    private String htScore;

    @JsonProperty("ft_score")
    private String ftScore;

    @JsonProperty("et_score")
    private String etScore;
    private String time;

    @JsonProperty("league_id")
    private String leagueId;
    private String status;
    private String added;

    @JsonProperty("last_changed")
    private String lastChanged;

    @JsonProperty("home_id")
    private String homeId;

    @JsonProperty("away_id")
    private String awayId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHtScore() {
        return htScore;
    }

    public void setHtScore(String htScore) {
        this.htScore = htScore;
    }

    public String getFtScore() {
        return ftScore;
    }

    public void setFtScore(String ftScore) {
        this.ftScore = ftScore;
    }

    public String getEtScore() {
        return etScore;
    }

    public void setEtScore(String etScore) {
        this.etScore = etScore;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(String lastChanged) {
        this.lastChanged = lastChanged;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getAwayId() {
        return awayId;
    }

    public void setAwayId(String awayId) {
        this.awayId = awayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id) &&
                Objects.equals(date, match.date) &&
                Objects.equals(homeName, match.homeName) &&
                Objects.equals(awayName, match.awayName) &&
                Objects.equals(score, match.score) &&
                Objects.equals(htScore, match.htScore) &&
                Objects.equals(ftScore, match.ftScore) &&
                Objects.equals(etScore, match.etScore) &&
                Objects.equals(time, match.time) &&
                Objects.equals(leagueId, match.leagueId) &&
                Objects.equals(status, match.status) &&
                Objects.equals(added, match.added) &&
                Objects.equals(lastChanged, match.lastChanged) &&
                Objects.equals(homeId, match.homeId) &&
                Objects.equals(awayId, match.awayId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, homeName, awayName, score, htScore, ftScore, etScore, time, leagueId, status, added, lastChanged, homeId, awayId);
    }
}
