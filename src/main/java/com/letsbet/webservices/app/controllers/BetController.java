package com.letsbet.webservices.app.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.resources.BetResource;
import com.letsbet.webservices.app.model.response.GeneralResponse;
import com.letsbet.webservices.app.security.FirebaseUserDetails;
import com.letsbet.webservices.app.services.BetService;
import com.letsbet.webservices.app.views.BetViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class BetController extends CommonController {

    private BetService betService;

    public BetController() {
    }

    @Autowired
    public BetController(BetService betService) {
        this.betService = betService;
    }

    @GetMapping(path = "/api/bets")
    public
    @JsonView(BetViews.Overview.class)
    List<BetResource> getUserBets(Principal principal) {
        FirebaseUserDetails details = getFirebaseUser(principal);
        return betService.getUserBets(details.getId());
    }

    @GetMapping(path = "/api/bets/{id}")
    public
    @JsonView(BetViews.Details.class)
    BetResource getBetDetails(@PathVariable("id") long id) {
        return betService.getBetDetails(id);
    }

    @PostMapping(path = "/api/bets")
    public GeneralResponse createBet(Principal principal,
                                     @RequestBody
                                     @JsonView(BetViews.Details.class) BetResource betResource) {
        FirebaseUserDetails details = getFirebaseUser(principal);
        Long id = betService.createBet(betResource, details.getId());
        if (id == -1) {
            return build400("Match already started", "WRONG_TIME");
        }
        return build201("bet created", id);
    }

    @DeleteMapping(path = "/api/bets/{id}")
    public GeneralResponse removeBet(Principal principal, @PathVariable("id") long id) {
        FirebaseUserDetails details = getFirebaseUser(principal);
        if (betService.removeBet(id, details.getId())) {
            return build200("bet removed");
        }
        return build400("couldn't remove bet", "USER_IS_NOT_BET_OWNER");
    }
}
