package com.letsbet.webservices.app.model.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.entities.Team;
import com.letsbet.webservices.app.views.GameViews;

public class TeamResource {

    private Long id;

    @JsonView(GameViews.Details.class)
    private String name;

    @JsonView(GameViews.Details.class)
    private String flagUrl;

    public TeamResource() {
    }

    public TeamResource(Team team) {
        if (team != null) {
            id = team.getId();
            name = team.getName();
            flagUrl = team.getFlagUrl();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}
