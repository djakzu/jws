package fr.epita.assistants.yakamon.converter;

import fr.epita.assistants.yakamon.data.model.GameModel;
import fr.epita.assistants.yakamon.domain.entity.GameEntity;
import fr.epita.assistants.yakamon.presentation.api.response.StartResponse;
import fr.epita.assistants.yakamon.utils.tile.CollectibleUtils;
import fr.epita.assistants.yakamon.utils.tile.TerrainType;
import fr.epita.assistants.yakamon.utils.tile.TileType;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameConverter {

    private List<List<TileType>> parseMap(String map) {
        List<List<TileType>> res = new ArrayList<>();
        int i = 0;
        while (i < map.length()) {
            List<TileType> lines = new ArrayList<>();
            int j = i;
            while (j < map.length() && map.charAt(j) != ';') {
                for (int k = 0; k < map.charAt(j) - '0'; k++) {
                    lines.add(new TileType(TerrainType.getTerrain(map.charAt(j + 1)), CollectibleUtils.getCollectible(map.charAt(j + 2))));
                }
                j+=3;
                i+=3;
            }
            res.add(lines);
            i++;
        }
        return res;
    }

    public GameEntity toEntity(GameModel model) {
        return new GameEntity(parseMap(model.getMap()));
    }

    public StartResponse toResponse(GameEntity entity) {
        return new StartResponse(entity.tiles);
    }
}