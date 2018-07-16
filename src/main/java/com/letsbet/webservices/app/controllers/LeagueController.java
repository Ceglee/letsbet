package com.letsbet.webservices.app.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.resources.BetResource;
import com.letsbet.webservices.app.model.resources.GameResource;
import com.letsbet.webservices.app.model.resources.LeagueResource;
import com.letsbet.webservices.app.model.resources.ScoreResource;
import com.letsbet.webservices.app.model.response.GeneralResponse;
import com.letsbet.webservices.app.security.FirebaseUserDetails;
import com.letsbet.webservices.app.services.BetService;
import com.letsbet.webservices.app.services.LeagueService;
import com.letsbet.webservices.app.services.ScoreService;
import com.letsbet.webservices.app.views.BetViews;
import com.letsbet.webservices.app.views.GameViews;
import com.letsbet.webservices.app.views.LeagueViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
public class LeagueController extends CommonController {

    private LeagueService leagueService;
    private BetService betService;
    private ScoreService scoreService;

    @Autowired
    public LeagueController(LeagueService leagueService, BetService betService, ScoreService scoreService) {
        this.leagueService = leagueService;
        this.betService = betService;
        this.scoreService = scoreService;
    }

    @PostMapping(path = "/api/leagues", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse createLeague(@RequestBody @JsonView(LeagueViews.Create.class) LeagueResource leagueResource,
                                        Principal principal) {
        FirebaseUserDetails userDetails = getFirebaseUser(principal);
        Long id = leagueService.createLeague(leagueResource, userDetails.getId());
        return build201("League created", id);
    }

    @GetMapping(path = "/api/leagues", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @JsonView(LeagueViews.Query.class)
    List<LeagueResource>
    searchForLeagues(@RequestParam(name = "name", required = false) String name,
                     @RequestParam(name = "pagination", required = false, defaultValue = "0") int pagination,
                     @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                     @RequestParam(name = "mine", required = false, defaultValue = "false") boolean mine,
                     Principal principal) {
        String uid = null;
        if (mine && principal != null) {
            FirebaseUserDetails userDetails = getFirebaseUser(principal);
            uid = userDetails.getId();
        }
        return StringUtils.isEmpty(name)
                ? leagueService.queryLeagues(pagination, page, uid)
                : Arrays.asList(leagueService.queryLeague(name, uid));
    }

    @GetMapping(path = "/api/leagues/{id}")
    public
    @JsonView(LeagueViews.Details.class)
    LeagueResource
    getLeagueDetails(@PathVariable("id") long id) {
        return leagueService.getLeagueDetails(id);
    }

    @GetMapping(path = "/api/leagues/{id}/games")
    public
    @JsonView(GameViews.Details.class)
    List<GameResource> getGamesForGivenLeague(@PathVariable("id") long id) {
        return leagueService.getLeagueGames(id);
    }

    @PutMapping(path = "/api/leagues/{id}/games/{gameId}")
    public GeneralResponse addGamesToGivenLeague(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        leagueService.addGameToLeague(id, gameId);
        return build200("game added");
    }

    @PutMapping(path = "/api/leagues/{id}/games")
    public GeneralResponse addGamesToGivenLeague(@PathVariable("id") long id,
                                                 @RequestBody
                                                 @JsonView(LeagueViews.Update.class) List<GameResource> games) {
        leagueService.addGamesToLeague(id, games);
        return build200("games added");
    }

    @DeleteMapping(path = "/api/leagues/{id}/games/{gameId}")
    public GeneralResponse removeGamesFromGivenLeague(@PathVariable("id") long id,
                                                      @PathVariable("gameId") long gameId) {
        leagueService.removeGameFromLeague(id, gameId);
        return build200("game removed");
    }

    @PutMapping(path = "/api/leagues/{id}/users")
    public GeneralResponse addUserToLeague(@PathVariable("id") long id, Principal principal) {
        FirebaseUserDetails userDetails = getFirebaseUser(principal);
        leagueService.addUserToLeague(id, userDetails.getId());
        return build200("user added");
    }

    @DeleteMapping(path = "/api/leagues/{id}/users")
    public GeneralResponse removeUserFromLeague(@PathVariable("id") long id, Principal principal) {
        FirebaseUserDetails userDetails = getFirebaseUser(principal);
        leagueService.removeUserFromLeague(id, userDetails.getId());
        return build200("user removed");

    }

    @GetMapping(path = "/api/leagues/{id}/bets")
    public
    @JsonView(BetViews.Overview.class)
    List<BetResource> getBetsForLeague(@PathVariable("id") long id,
                                       @RequestParam(name = "mine", defaultValue = "false") boolean mine,
                                       Principal principal) {
        if (mine) {
            FirebaseUserDetails user = getFirebaseUser(principal);
            return betService.getBetsForLeague(id, user.getId());
        } else {
            return betService.getBetsForLeague(id);
        }
    }

    @GetMapping(path = "/api/leagues/{id}/bets/{userId}")
    public
    @JsonView(BetViews.Overview.class)
    List<BetResource> getUserBetsForLeague(@PathVariable("id") long id,
                                           @PathVariable("userId") String userId) {
        return betService.getBetsForUserInLeague(id, userId);
    }

    @GetMapping(path = "/api/leagues/{id}/scores")
    public List<ScoreResource> getScores(@PathVariable("id") long id) {
        return scoreService.getScoresForLeague(id);
    }
}
