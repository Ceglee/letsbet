package com.letsbet.webservices.app.services.impl;

import com.letsbet.webservices.app.dao.BetDAO;
import com.letsbet.webservices.app.model.entities.Bet;
import com.letsbet.webservices.app.model.entities.League;
import com.letsbet.webservices.app.model.resources.ScoreResource;
import com.letsbet.webservices.app.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private BetDAO betDAO;

    @Override
    @Transactional
    public List<ScoreResource> getScoresForLeague(long leagueId) {
        List<Bet> betsForLeague = betDAO.getBetsForLeague(leagueId);
        List<ScoreResource> scores = new LinkedList<>();
        Map<Long, List<ScoreResource>> results = betsForLeague.stream()
                .filter(Bet::getWithResult)
                .map(ScoreResource::new)
                .collect(Collectors.groupingBy(ScoreResource::getUserId));
        results.forEach((id, scoreResources) -> scores.add(sum(id, scoreResources)));
        return scores;
    }

    @Override
    public ScoreResource toResource(League entity) {
        return null;
    }

    @Override
    public League toEntity(ScoreResource resource) {
        return null;
    }

    private ScoreResource sum(Long id, List<ScoreResource> scores) {
        if (scores != null) {
            int sum = 0;
            for (ScoreResource score : scores) {
                sum += score.getScore();
            }
            return new ScoreResource(id, sum);
        }
        return new ScoreResource();
    }
}
