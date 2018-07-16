package com.letsbet.webservices.app.dao.impl;

import com.letsbet.webservices.app.dao.GameDAO;
import com.letsbet.webservices.app.model.entities.Game;
import com.letsbet.webservices.app.model.entities.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PostgresGameDAO implements GameDAO {

    private EntityManager entityManager;

    @Autowired
    public PostgresGameDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Game getById(Long id) {
        return entityManager.find(Game.class, id);
    }

    @Override
    public List<Game> getGamesToUpdate() {
        return entityManager.createNamedQuery("query_finished_games_with_no_result", Game.class)
                .setParameter("now", LocalDateTime.now().minusHours(2))
                .getResultList();
    }

    @Override
    public void save(Game game) {
        entityManager.persist(game);
    }
}
