package fr.epita.assistants.yakamon.domain.service;

import fr.epita.assistants.yakamon.converter.GameConverter;
import fr.epita.assistants.yakamon.converter.PlayerConverter;
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
public class PlayerService {

    @Inject
    PlayerRepository playerRepository;

    public PlayerModel getPlayerModel() {
        PlayerModel model = playerRepository.findAll().firstResult();
        if (model == null) {
            throw new WebApplicationException("{\"message\": \"Game not started\"}", 400);
        }
        return model;
    }
}