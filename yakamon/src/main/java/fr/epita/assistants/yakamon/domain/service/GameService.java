package fr.epita.assistants.yakamon.domain.service;

import fr.epita.assistants.yakamon.converter.GameConverter;
import fr.epita.assistants.yakamon.data.model.*;
import fr.epita.assistants.yakamon.data.repository.*;
import fr.epita.assistants.yakamon.domain.entity.GameEntity;
import fr.epita.assistants.yakamon.presentation.api.request.StartRequest;
import fr.epita.assistants.yakamon.utils.tile.ItemType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;

import java.io.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class GameService {
    @Inject
    GameRepository gameRepository;
    @Inject
    ItemRepository itemRepository;
    @Inject
    PlayerRepository playerRepository;
    @Inject
    YakadexEntryRepository yakadexEntryRepository;
    @Inject
    YakamonRepository yakamonRepository;
    @Inject
    GameConverter gameConverter;


    private void initPlayer(String name) {
        playerRepository.deleteAll();
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName(name);
        playerModel.setPosX(0);
        playerModel.setPosY(0);
        playerModel.setLastFeed(null);
        playerModel.setLastCatch(null);
        playerModel.setLastCollect(null);
        playerModel.setLastMove(null);
        playerRepository.persist(playerModel);
    }

    private void initItem() {
        itemRepository.deleteAll();
        ItemModel itemModel = new ItemModel();
        itemModel.type = ItemType.YAKABALL;
        itemModel.quantity = 5;
        itemRepository.persist(itemModel);
    }

    private void initYakamon() {
        yakamonRepository.deleteAll();
        YakamonModel yakamonModel = new YakamonModel();
        yakamonModel.setNickname(null);
        yakamonModel.setEnergyPoints(null);
        yakamonModel.setYakadexEntry(null);
        yakamonRepository.persist(yakamonModel);
    }

    private void initYakadexEntry() {
        yakadexEntryRepository.findAll().stream().map(i -> i.caught = false);
    }

    @Transactional
    public GameEntity start(StartRequest request) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(request.getMapPath()));
            String str = br.lines().collect(Collectors.joining("\n"));

            initPlayer(request.getPlayerName());
            initItem();
            //initYakamon();
            //initYakadexEntry();

            gameRepository.deleteAll();

            GameModel model = new GameModel();
            model.setMap(str);

            GameEntity entity = gameConverter.toEntity(model);
            gameRepository.persist(model);

            return entity;

        } catch (Exception e) {
            throw new RuntimeException();
        }
        finally {
            if (br != null) {try {br.close();} catch (Exception e) {
                System.err.println(e.getMessage());
            }}
        }
    }
}
