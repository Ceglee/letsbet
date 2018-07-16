package com.letsbet.webservices.app.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.entities.User;
import com.letsbet.webservices.app.views.LeagueViews;
import com.letsbet.webservices.app.views.UserViews;

import java.util.Objects;

public class UserResource {

    @JsonView({LeagueViews.Details.class,UserViews.Details.class, UserViews.PublicData.class, UserViews.Create.class})
    private Long id;

    @JsonView({LeagueViews.Details.class,UserViews.Details.class, UserViews.PublicData.class, UserViews.Create.class})
    private String username;

    @JsonView({UserViews.Details.class, UserViews.PublicData.class, UserViews.Create.class})
    private String avatarUrl;

    @JsonView({UserViews.Details.class, UserViews.PublicData.class, UserViews.Create.class})
    private Integer points;

    @JsonView({UserViews.Details.class, UserViews.Create.class})
    @JsonProperty("isPremiumUser")
    private Boolean premium;

    public UserResource() {
    }

    public UserResource(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.avatarUrl = user.getAvatarUrl();
        this.points = user.getPoints();
        this.premium = user.getPremium();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResource)) return false;
        UserResource that = (UserResource) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(points, that.points) &&
                Objects.equals(premium, that.premium);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, avatarUrl, points, premium);
    }
}
