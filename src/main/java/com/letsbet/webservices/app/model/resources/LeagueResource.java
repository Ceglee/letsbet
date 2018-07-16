package com.letsbet.webservices.app.model.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.entities.User;
import com.letsbet.webservices.app.views.LeagueViews;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LeagueResource {

    @JsonView({LeagueViews.Query.class })
    private Long id;

    @JsonView({LeagueViews.Query.class, LeagueViews.Details.class, LeagueViews.Create.class, LeagueViews.Query.class})
    private String name;

    @JsonView({LeagueViews.Query.class, LeagueViews.Details.class, LeagueViews.Create.class, LeagueViews.Query.class})
    private Boolean isPrivate;

    @JsonView({LeagueViews.Query.class, LeagueViews.Details.class, LeagueViews.Create.class, LeagueViews.Query.class})
    private String description;

    @JsonView(LeagueViews.Details.class)
    private List<GameResource> games;

    @JsonView({LeagueViews.Query.class, LeagueViews.Details.class})
    private List<UserResource> users;

    public LeagueResource() {
    }

    public LeagueResource(League league) {
        if (league != null) {
            id = league.getId();
            name = league.getName();
            isPrivate = league.getPrivate();
            description = league.getDescription();

            games = new LinkedList<>();
            for (Game game: league.getGames()) {
                games.add(new GameResource(game));
            }

            users = new LinkedList<>();
            for (User user: league.getUsers()) {
                users.add(new UserResource(user));
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GameResource> getGames() {
        return games;
    }

    public void setGames(List<GameResource> games) {
        this.games = games;
    }

    public List<UserResource> getUsers() {
        return users;
    }

    public void setUsers(List<UserResource> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeagueResource)) return false;
        LeagueResource that = (LeagueResource) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(isPrivate, that.isPrivate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(games, that.games) &&
                Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, isPrivate, description, games, users);
    }
}
