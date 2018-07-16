package com.letsbet.webservices.app.dao;

import com.letsbet.webservices.app.model.entities.Game;

import java.util.List;

public interface GameDAO {

    Game getById(Long id);
    List<Game> getGamesToUpdate();
    void save(Game game);
}
