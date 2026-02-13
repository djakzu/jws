package fr.epita.assistants.yakamon.converter;

import fr.epita.assistants.yakamon.data.model.PlayerModel;
import fr.epita.assistants.yakamon.domain.entity.PlayerEntity;
import fr.epita.assistants.yakamon.presentation.api.response.MoveResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerConverter {

    public PlayerEntity toEntity(PlayerModel model) {
        return new PlayerEntity(model.getName(), model.getPosX(), model.getPosY(), model.getLastMove(), model.getLastCatch(), model.getLastCollect(), model.getLastFeed());
    }

    public PlayerModel toModel(PlayerEntity entity, PlayerModel existingModel) {
        existingModel.setPosX(entity.x);
        existingModel.setPosY(entity.y);
        existingModel.setLastMove(entity.lastMove);
        existingModel.setLastCollect(entity.lastCollect);
        existingModel.setLastCatch(entity.lastCatch);
        existingModel.setLastFeed(entity.lastFeed);
        return existingModel;
    }

    public MoveResponse toResponse(PlayerEntity entity) {
        return new MoveResponse(entity.x, entity.y);
    }
}