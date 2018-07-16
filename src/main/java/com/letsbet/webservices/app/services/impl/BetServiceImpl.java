package com.letsbet.webservices.app.services.impl;

import com.letsbet.webservices.app.dao.BetDAO;
import com.letsbet.webservices.app.dao.GameDAO;
import com.letsbet.webservices.app.dao.LeagueDAO;
import com.letsbet.webservices.app.dao.UserDAO;
import com.letsbet.webservices.app.model.entities.Bet;
import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.entities.User;
import com.letsbet.webservices.app.model.resources.BetResource;
import com.letsbet.webservices.app.services.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetServiceImpl implements BetService {

    private BetDAO betDAO;
    private LeagueDAO leagueDAO;
    private GameDAO gameDAO;
    private UserDAO userDAO;

    public BetServiceImpl() {
    }

    @Autowired
    public BetServiceImpl(BetDAO betDAO, LeagueDAO leagueDAO, GameDAO gameDAO, UserDAO userDAO) {
        this.betDAO = betDAO;
        this.leagueDAO = leagueDAO;
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<BetResource> getUserBets(String uid) {
        return betDAO.getUserBets(uid).stream().map(this::toResource).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BetResource> getBetsForLeague(Long leagueId) {
        return betDAO.getBetsForLeague(leagueId).stream().map(this::toResource).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BetResource> getBetsForLeague(Long leagueId, String uid) {
        return betDAO.getBetsForLeague(leagueId, uid).stream().map(this::toResource).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BetResource> getBetsForUserInLeague(Long leagueId, String userId) {
        return betDAO.getBetsForUserInLeague(leagueId, userId).stream().map(this::toResource).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BetResource getBetDetails(Long betId) {
        return toResource(betDAO.getBetById(betId));
    }

    @Override
    @Transactional
    public Long createBet(BetResource resource, String uid) {
        Bet bet = toEntity(resource);
        User user = userDAO.getUserByUID(uid);
        League league = leagueDAO.getById(resource.getLeagueId());
        Game game = gameDAO.getById(resource.getGameId());
        bet.setUser(user);
        bet.setLeague(league);
        bet.setGame(game);

        return betDAO.createBet(bet);
    }

    @Override
    @Transactional
    public boolean removeBet(Long betId, String uid) {
        Bet bet = betDAO.getBetById(betId);
        if (bet != null && bet.getUser() != null && uid != null
                && uid.equals(bet.getUser().getUid())) { // Requesting user is a bet owner
            betDAO.removeBet(bet);
            return true;
        }
        return false;
    }

    @Override
    public BetResource toResource(Bet entity) {
        return entity != null ? new BetResource(entity) : null;
    }

    @Override
    public Bet toEntity(BetResource resource) {
        return resource != null ? new Bet(resource) : null;
    }
}
