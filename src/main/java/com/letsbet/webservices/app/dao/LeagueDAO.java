package com.letsbet.webservices.app.dao;

import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.entities.User;

import java.util.List;
import java.util.Set;

public interface LeagueDAO {

    League createLeague(League league);
    League getByName(String name);
    League getByName(String name, String uid);
    League getById(Long id);
    List<League> queryPerPage(int pagination, int page);
    List<League> queryPerPage(int pagination, int page, String uid);
    void addGamesToLeague(Long id, Set<Game> games);
    void removeGamesFromLeague(Long id, Set<Game> games);
    void addUserToLeague(Long id, User user);
    void removeUserFromLeague(Long id, User user);
}
