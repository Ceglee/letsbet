package com.letsbet.webservices.app.schedulers;

import com.letsbet.webservices.app.dao.BetDAO;
import com.letsbet.webservices.app.dao.GameDAO;
import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.livescore.Match;
import com.letsbet.webservices.app.model.livescore.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScoreTask {

    private static final String STATUS_FINISHED = "FINISHED";

    @Value("${livescore.url}")
    public String url;

    @Value("${livescore.key}")
    public String key;

    @Value("${livescore.secret}")
    public String secret;

    private GameDAO gameDAO;
    private BetDAO betDAO;
    private RestTemplate restTemplate;
    private ResponseWrapper wrapper;

    @Autowired
    public UserScoreTask(GameDAO gameDAO, RestTemplate restTemplate, BetDAO betDAO) {
        this.gameDAO = gameDAO;
        this.betDAO = betDAO;
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedDelay = 600000)
    @Transactional
    public void updateAllPastMatches() {
        String[] leagueIds = {"793", "794", "795", "796", "797", "798", "799", "800", "801", "802", "803", "804", "805"};

        List<Game> games = gameDAO.getGamesToUpdate();
        if (games != null && !games.isEmpty()) {
            for (String leagueId : leagueIds) {
                Map<String, String> queryParams = new HashMap<>();
                queryParams.put("key", key);
                queryParams.put("secret", secret);
                queryParams.put("league", leagueId);
                ResponseEntity<ResponseWrapper> response = restTemplate.getForEntity(url, ResponseWrapper.class, queryParams);
                updateAllPastMatches(games, response);
            }
        }
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void updateLiveScores() {
        List<Game> games = gameDAO.getGamesToUpdate();
        if (games != null && !games.isEmpty()) {
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("key", key);
            queryParams.put("secret", secret);
            ResponseEntity<ResponseWrapper> response = restTemplate.getForEntity("http://livescore-api.com/api-client/scores/live.json?key={key}&secret={secret}", ResponseWrapper.class, queryParams);
            updateAllPastMatches(games, response);
        }
    }

    private void updateAllPastMatches(List<Game> games, ResponseEntity<ResponseWrapper> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            wrapper = response.getBody();
            List<Game> gamesToUpdate = games.stream()
                    .filter(this::canUpdate)
                    .collect(Collectors.toList());
            gamesToUpdate.forEach(this::updateGame);
            gamesToUpdate.forEach(this::updateBets);
        }
    }

    private boolean canUpdate(final Game game) {
        String[] teams = parseTeamName(game.getTitle());
        Match match = findByTeams(teams[0], teams[1]);
        return match != null && STATUS_FINISHED.equals(match.getStatus());
    }

    private void updateGame(final Game game) {
        if (game != null) {
            String[] teams = parseTeamName(game.getTitle());
            Match match = findByTeams(teams[0], teams[1]);
            if (match != null) {
                Short[] score = parseScore(match.getFtScore());
                if (score != null) {
                    game.setTeamHomeResult(score[0]);
                    game.setTeamAwayResult(score[1]);
                    game.setWinnerTeam((short) (score[0] > score[1] ? 1 : 2));
                    gameDAO.save(game);
                }
            }
        }
    }

    private Match findByTeams(String homeTeam, String awayTeam) {
        if (wrapper != null && wrapper.getData() != null && wrapper.getData().getMatch() != null) {
            return wrapper.getData().getMatch().stream()
                    .filter(match -> {
                        String matchHomeTeam = match != null ? match.getHomeName() : "";
                        String matchAwayTeam = match != null ? match.getAwayName() : "";
                        return homeTeam.equals(matchHomeTeam) && awayTeam.equals(matchAwayTeam);
                    })
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private Short[] parseScore(String score) {
        if (score != null) {
            String[] rawScore = score.split(" - ");
            if (rawScore.length >= 2) {
                return new Short[]{Short.parseShort(rawScore[0]), Short.parseShort(rawScore[1])};
            }
        }
        return null;
    }

    private String[] parseTeamName(String versusText) {
        if (versusText != null) {
            String[] rawScore = versusText.split(" vs ");
            if (rawScore.length >= 2) {
                return rawScore;
            }
        }
        return new String[]{"", ""};
    }

    private void updateBets(final Game game) {
        game.getBets().forEach(bet -> {
            if (bet.getAwayScore() != null && bet.getHomeScore() != null && game.getTeamAwayResult() != null
                    && game.getTeamHomeResult() != null) {
                short betAway = bet.getAwayScore();
                short betHome = bet.getHomeScore();

                short gameAway = game.getTeamAwayResult();
                short gameHome = game.getTeamHomeResult();

                int extraPoint = game.getWinnerTeam() != null
                        && bet.getWinnerTeam() != null
                        && game.getWinnerTeam().equals(bet.getWinnerTeam()) ? 1 : 0;
                if (betAway == gameAway && betHome == gameHome) {
                    bet.setPoints((short) (3 + extraPoint));
                } else if (getResult((short) (betAway - betHome)) == getResult((short) (gameAway - gameHome))) {
                    bet.setPoints((short) (1 + extraPoint));
                } else {
                    bet.setPoints((short) (1 + extraPoint));
                }
                bet.setWithResult(true);
                betDAO.save(bet);
            }
        });
    }

    private short getResult(short result) {
        return (short) (result == 0 ? 0 : result < 0 ? -1 : 1);
    }
}
