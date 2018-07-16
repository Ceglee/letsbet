package com.letsbet.webservices.app.model.entities;

import com.letsbet.webservices.app.model.resources.LeagueResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "get_public_league_by_name",
                query = "from League as l where l.name = :name and l.isPrivate is false"
        ),
        @NamedQuery(
                name = "get_users_league_by_name",
                query = "select l from League as l inner join l.users as u where u.uid = :uid and l.name = :name"
        ),
        @NamedQuery(
                name = "query_public_leagues_per_page",
                query = "from League as l where l.isPrivate is false"
        ),
        @NamedQuery(
                name = "query_users_leagues_per_page",
                query = "select l from League as l inner join l.users as u where u.uid = :uid"
        )
})
@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "is_private")
    private Boolean isPrivate;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "game_league",
            joinColumns = { @JoinColumn(name = "league_id") },
            inverseJoinColumns = { @JoinColumn(name = "game_id") }
    )
    private Set<Game> games = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_league",
            joinColumns = { @JoinColumn(name = "league_id") },
            inverseJoinColumns = { @JoinColumn(name = "account_id") }
    )
    private Set<User> users = new HashSet<>();

    public League() {
    }

    public League(LeagueResource resource) {
        if (resource != null) {
            name = resource.getName();
            isPrivate = resource.getPrivate();
            description = resource.getDescription();
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

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof League)) return false;
        League league = (League) o;
        return Objects.equals(id, league.id) &&
                Objects.equals(name, league.name) &&
                Objects.equals(isPrivate, league.isPrivate) &&
                Objects.equals(description, league.description) &&
                Objects.equals(games, league.games) &&
                Objects.equals(users, league.users);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, isPrivate, description, games, users);
    }
}
