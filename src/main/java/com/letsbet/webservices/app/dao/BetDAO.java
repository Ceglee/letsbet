package com.letsbet.webservices.app.dao;

import com.letsbet.webservices.app.model.entities.Bet;

import java.util.List;

public interface BetDAO {

    List<Bet> getUserBets(String uid);
    List<Bet> getBetsForLeague(Long leagueId);
    List<Bet> getBetsForLeague(Long leagueId, String uid);
    List<Bet> getBetsForUserInLeague(Long leagueId, String userId);
    Bet getBetById(Long id);
    Long createBet(Bet bet);
    void removeBet(Bet bet);
    void save(Bet bet);
}
