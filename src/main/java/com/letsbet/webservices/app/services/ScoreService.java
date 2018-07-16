package com.letsbet.webservices.app.services;

import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.resources.ScoreResource;

import java.util.List;

public interface ScoreService extends Service<ScoreResource, League> {

    List<ScoreResource> getScoresForLeague(long leagueId);
}
