package com.letsbet.webservices.app.services.impl;

import com.letsbet.webservices.app.dao.GameDAO;
import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.resources.GameResource;
import com.letsbet.webservices.app.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GameServiceImpl implements GameService {

    private GameDAO gameDAO;

    @Autowired
    public GameServiceImpl(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    @Override
    @Transactional
    public GameResource getGameDetails(Long gameId) {
        return toResource(gameDAO.getById(gameId));
    }

    @Override
    public GameResource toResource(Game entity) {
        return entity != null
                ? new GameResource(entity)
                : null;
    }

    @Override
    public Game toEntity(GameResource resource) {
        return null;
    }
}
