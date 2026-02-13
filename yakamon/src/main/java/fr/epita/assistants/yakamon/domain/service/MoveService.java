package fr.epita.assistants.yakamon.domain.service;

import fr.epita.assistants.yakamon.converter.GameConverter;
import fr.epita.assistants.yakamon.data.model.GameModel;
import fr.epita.assistants.yakamon.data.model.PlayerModel;
import fr.epita.assistants.yakamon.data.repository.GameRepository;
import fr.epita.assistants.yakamon.data.repository.PlayerRepository;
import fr.epita.assistants.yakamon.domain.entity.GameEntity;
import fr.epita.assistants.yakamon.utils.Direction;
import fr.epita.assistants.yakamon.utils.Point;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.time.LocalDateTime;

@ApplicationScoped
public class MoveService {
    @Inject
    PlayerRepository playerRepository;
    @Inject
    GameRepository gameRepository;
    @Inject
    GameConverter gameConverter;

    @Transactional
    public PlayerModel movePlayer(Direction direction) {

        PlayerModel model = playerRepository.findAll().firstResult();
        if (model == null) {
            return null;
        }

        GameModel gameModel = gameRepository.findAll().firstResult();
        GameEntity gameEntity = gameConverter.toEntity(gameModel);
        Point moveVector = direction.getPoint();
        Integer newX = model.getPosX() + moveVector.getPosX();
        Integer newY = model.getPosY() + moveVector.getPosY();
        if (newX < 0 || newX > gameEntity.tiles.getFirst().size() - 1 || newY < 0 || newY > gameEntity.tiles.size() - 1) {
            return null;
        }
        model.setPosX(newX);
        model.setPosY(newY);
        model.setLastMove(LocalDateTime.now());

        playerRepository.persist(model);

        return model;
    }
}
