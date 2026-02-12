package fr.epita.assistants.yakamon.domain.service;

import fr.epita.assistants.yakamon.converter.PlayerConverter;
import fr.epita.assistants.yakamon.data.model.PlayerModel;
import fr.epita.assistants.yakamon.data.repository.PlayerRepository;
import fr.epita.assistants.yakamon.domain.entity.PlayerEntity;
import fr.epita.assistants.yakamon.utils.Direction;
import fr.epita.assistants.yakamon.utils.Point;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class PlayerService {

    @Inject PlayerRepository playerRepository;
    @Inject PlayerConverter playerConverter;

    @ConfigProperty(name = "JWS_TICK_DURATION") int tickDuration;
    @ConfigProperty(name = "JWS_MOVEMENT_DELAY") int movementDelay;

    @Transactional
    public PlayerEntity movePlayer(Direction direction) {

        PlayerModel model = playerRepository.findAll().firstResult();
        if (model == null) {
            throw new WebApplicationException("Game not started", 400);
        }

        PlayerEntity entity = playerConverter.toEntity(model);

        if (entity.lastMove != null) {
            long ticksSinceLastMove = ChronoUnit.MILLIS.between(entity.lastMove, LocalDateTime.now()) / tickDuration;
            if (ticksSinceLastMove < movementDelay) {
                throw new WebApplicationException("Too fast!", 429);
            }
        }

        Point moveVector = direction.getPoint();

        entity.x += moveVector.getPosX();
        entity.y += moveVector.getPosY();

        entity.lastMove = LocalDateTime.now();

        PlayerModel updatedModel = playerConverter.toModel(entity, model);
        playerRepository.persist(updatedModel);

        return entity;
    }
}