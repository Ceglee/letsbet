package com.letsbet.webservices.app.services;

import com.letsbet.webservices.app.model.entities.Bet;
import com.letsbet.webservices.app.model.resources.BetResource;

import java.util.List;

public interface BetService extends Service<BetResource, Bet>  {

    List<BetResource> getUserBets(String uid);
    List<BetResource> getBetsForLeague(Long leagueId);
    List<BetResource> getBetsForLeague(Long leagueId, String uid);
    List<BetResource> getBetsForUserInLeague(Long leagueId, String userId);
    BetResource getBetDetails(Long betId);
    Long createBet(BetResource resource, String uid);
    boolean removeBet(Long betId, String uid);
}
