package com.letsbet.webservices.app.services.impl;

import com.letsbet.webservices.app.dao.GameDAO;
import com.letsbet.webservices.app.dao.LeagueDAO;
import com.letsbet.webservices.app.dao.UserDAO;
import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.entities.User;
import com.letsbet.webservices.app.model.resources.GameResource;
import com.letsbet.webservices.app.model.resources.LeagueResource;
import com.letsbet.webservices.app.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LeagueServiceImpl implements LeagueService {

    private LeagueDAO leagueDAO;
    private GameDAO gameDAO;
    private UserDAO userDAO;

    @Autowired
    public LeagueServiceImpl(LeagueDAO leagueDAO, GameDAO gameDAO, UserDAO userDAO) {
        this.leagueDAO = leagueDAO;
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public Long createLeague(LeagueResource leagueResource, String uid) {
        User user = userDAO.getUserByUID(uid);
        League league = toEntity(leagueResource);
        if (league != null) {
            league.getUsers().add(user);
            league = leagueDAO.createLeague(league);
            return league.getId();
        }
        return null;
    }

    @Override
    @Transactional
    public LeagueResource queryLeague(String name, String uid) {
        League league = uid != null && !uid.isEmpty()
                ? leagueDAO.getByName(name, uid)
                : leagueDAO.getByName(name);
        return toResource(league);
    }

    @Override
    @Transactional
    public List<LeagueResource> queryLeagues(int pagination, int page, String uid) {
        page = page > 0 ? page : 1;
        List<League> leagues = uid != null && !uid.isEmpty()
                ? leagueDAO.queryPerPage(pagination, page, uid)
                : leagueDAO.queryPerPage(pagination, page);
        if (leagues != null) {
            return leagues.stream().map(this::toResource).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public LeagueResource getLeagueDetails(long leagueId) {
        return toResource(leagueDAO.getById(leagueId));
    }

    @Override
    @Transactional
    public List<GameResource> getLeagueGames(long leagueId) {
        LeagueResource leagueResource = toResource(leagueDAO.getById(leagueId));
        return leagueResource != null
                ? leagueResource.getGames()
                : new LinkedList<>();
    }

    @Override
    @Transactional
    public void addGameToLeague(long leagueId, long gameId) {
        GameResource game = new GameResource();
        game.setId(gameId);
        addGamesToLeague(leagueId, Arrays.asList(game));
    }

    @Override
    @Transactional
    public void addGamesToLeague(long leagueId, List<GameResource> games) {
        Set<Game> pGames = games.stream().map(GameResource::getId).map(gameDAO::getById).collect(Collectors.toSet());
        if (!pGames.isEmpty()) {
            leagueDAO.addGamesToLeague(leagueId, pGames);
        }
    }

    @Override
    @Transactional
    public void removeGameFromLeague(long leagueId, long gameId) {
        GameResource game = new GameResource();
        game.setId(gameId);
        removeGamesFromLeague(leagueId, Arrays.asList(game));
    }

    @Override
    @Transactional
    public void removeGamesFromLeague(long leagueId, List<GameResource> games) {
        Set<Game> pGames = games.stream().map(GameResource::getId).map(gameDAO::getById).collect(Collectors.toSet());
        if (!pGames.isEmpty()) {
            leagueDAO.removeGamesFromLeague(leagueId, pGames);
        }
    }

    @Override
    @Transactional
    public void addUserToLeague(long leagueId, String uid) {
        User user = userDAO.getUserByUID(uid);
        if (user != null) {
            leagueDAO.addUserToLeague(leagueId, user);
        }
    }

    @Override
    @Transactional
    public void removeUserFromLeague(long leagueId, String uid) {
        User user = userDAO.getUserByUID(uid);
        if (user != null) {
            leagueDAO.removeUserFromLeague(leagueId, user);
        }
    }

    @Override
    public LeagueResource toResource(League league) {
        return league != null ? new LeagueResource(league) : null;

    }

    @Override
    public League toEntity(LeagueResource resource) {
        return resource != null ? new League(resource) : null;
    }
}
