package com.letsbet.webservices.app.model.entities;

import com.letsbet.webservices.app.model.resources.UserResource;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name = "get_user_by_uid",
                query = "from User as u where u.uid = :uid"
        )
})
@Entity
@Table(name = "Account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(name = "avatar_url")
    private String avatarUrl;
    private Integer points;
    private Boolean premium;
    private String uid;

    public User() {
    }

    public User(UserResource user) {
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(avatarUrl, user.avatarUrl) &&
                Objects.equals(points, user.points) &&
                Objects.equals(premium, user.premium) &&
                Objects.equals(uid, user.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, avatarUrl, points, premium, uid);
    }
}
