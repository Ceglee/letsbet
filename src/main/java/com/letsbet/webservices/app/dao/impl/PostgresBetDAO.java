package com.letsbet.webservices.app.dao.impl;

import com.letsbet.webservices.app.dao.BetDAO;
import com.letsbet.webservices.app.model.entities.Bet;
import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostgresBetDAO implements BetDAO {

    private EntityManager entityManager;

    public PostgresBetDAO() {
    }

    @Autowired
    public PostgresBetDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Bet> getUserBets(String uid) {
        return entityManager.createNamedQuery("get_user_bets", Bet.class)
                .setParameter("uid", uid)
                .getResultList();
    }

    @Override
    public List<Bet> getBetsForLeague(Long leagueId) {
        return entityManager.createNamedQuery("get_bets_for_league", Bet.class)
                .setParameter("id", leagueId)
                .getResultList();
    }

    @Override
    public List<Bet> getBetsForLeague(Long leagueId, String uid) {
        return entityManager.createNamedQuery("get_user_bets_for_league", Bet.class)
                .setParameter("id", leagueId)
                .setParameter("uid", uid)
                .getResultList();
    }

    @Override
    public List<Bet> getBetsForUserInLeague(Long leagueId, String userId) {
        return entityManager.createNamedQuery("get_other_user_bets_for_league", Bet.class)
                .setParameter("id", leagueId)
                .setParameter("userId", Long.parseLong(userId))
                .getResultList();
    }

    @Override
    public Bet getBetById(Long id) {
        return entityManager.find(Bet.class, id);
    }

    @Override
    public Long createBet(Bet bet) {
        entityManager.persist(bet);
        return bet.getId();
    }

    @Override
    public void removeBet(Bet bet) {
        entityManager.remove(bet);
    }

    @Override
    public void save(Bet bet) {
        entityManager.persist(bet);
    }
}
