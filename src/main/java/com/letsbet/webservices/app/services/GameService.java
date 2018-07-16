package com.letsbet.webservices.app.services;

import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.resources.BetResource;
import com.letsbet.webservices.app.model.resources.GameResource;

import java.util.List;

public interface GameService extends Service<GameResource, Game> {

    GameResource getGameDetails(Long gameId);
}
