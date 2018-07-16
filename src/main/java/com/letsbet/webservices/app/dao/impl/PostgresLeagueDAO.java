package com.letsbet.webservices.app.dao.impl;

import com.letsbet.webservices.app.dao.LeagueDAO;
import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Repository
public class PostgresLeagueDAO implements LeagueDAO {

    private EntityManager entityManager;

    @Autowired
    public PostgresLeagueDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public League createLeague(League league) {
        entityManager.persist(league);
        return league;
    }

    @Override
    public League getByName(String name) {
        return entityManager.createNamedQuery("get_public_league_by_name", League.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public League getByName(String name, String uid) {
        return entityManager.createNamedQuery("get_users_league_by_name", League.class)
                .setParameter("name", name)
                .setParameter("uid", uid)
                .getSingleResult();
    }

    @Override
    public League getById(Long id) {
        return entityManager.find(League.class, id);
    }

    @Override
    public List<League> queryPerPage(int pagination, int page) {
        return entityManager
                .createNamedQuery( "query_public_leagues_per_page", League.class )
                .setFirstResult(pagination * (page - 1))
                .setMaxResults(pagination)
                .getResultList();
    }

    @Override
    public List<League> queryPerPage(int pagination, int page, String uid) {
        return entityManager
                .createNamedQuery( "query_users_leagues_per_page", League.class )
                .setParameter("uid", uid)
                .setFirstResult(pagination * (page - 1))
                .setMaxResults(pagination)
                .getResultList();
    }

    @Override
    public void addGamesToLeague(Long id, Set<Game> games) {
        League league = getById(id);
        if (league != null) {
            league.getGames().addAll(games);
            entityManager.persist(league);
        }
    }

    @Override
    public void removeGamesFromLeague(Long id, Set<Game> games) {
        League league = getById(id);
        if (league != null) {
            league.getGames().removeAll(games);
            entityManager.persist(league);
        }
    }

    @Override
    public void addUserToLeague(Long id, User user) {
        League league = getById(id);
        if (league != null && !league.getUsers().contains(user)) {
            league.getUsers().add(user);
            entityManager.persist(league);
        }
    }

    @Override
    public void removeUserFromLeague(Long id, User user) {
        League league = getById(id);
        if (league != null && league.getUsers().contains(user)) {
            league.getUsers().remove(user);
            entityManager.persist(league);
        }
    }
}

