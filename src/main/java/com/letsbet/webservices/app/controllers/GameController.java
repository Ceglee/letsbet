package com.letsbet.webservices.app.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.resources.GameResource;
import com.letsbet.webservices.app.services.GameService;
import com.letsbet.webservices.app.views.GameViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController extends CommonController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/api/games/{id}")
    public
    @JsonView(GameViews.Details.class)
    GameResource getGameDetails(@PathVariable("id") long id) {
        return gameService.getGameDetails(id);
    }

}
