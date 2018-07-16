package com.letsbet.webservices.app.services;

import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.resources.GameResource;
import com.letsbet.webservices.app.model.resources.LeagueResource;

import java.util.List;

public interface LeagueService extends Service<LeagueResource, League> {

    Long createLeague(LeagueResource leagueResource, String uid);
    LeagueResource queryLeague(String name, String uid);
    List<LeagueResource> queryLeagues(int pagination, int page, String uid);
    LeagueResource getLeagueDetails(long leagueId);
    List<GameResource> getLeagueGames(long leagueId);
    void addGameToLeague(long leagueId, long gameId);
    void addGamesToLeague(long leagueId, List<GameResource> games);
    void removeGameFromLeague(long leagueId, long gameId);
    void removeGamesFromLeague(long leagueId, List<GameResource> games);
    void addUserToLeague(long leagueId, String uid);
    void removeUserFromLeague(long leagueId, String uid);
}
