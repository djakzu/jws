package fr.epita.assistants.yakamon.domain.service;

import fr.epita.assistants.yakamon.data.model.PlayerModel;
import fr.epita.assistants.yakamon.data.repository.GameRepository;
import fr.epita.assistants.yakamon.data.repository.ItemRepository;
import fr.epita.assistants.yakamon.utils.Item;
import fr.epita.assistants.yakamon.utils.tile.ItemType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemService {
    @Inject
    ItemRepository itemRepository;
    @Inject
    GameRepository gameRepository;

    public List<Item> inventory() {
        if (gameRepository.listAll().isEmpty()) {
            throw new WebApplicationException("Game not started", 400);
        }

        Integer scrooge = 0;
        Integer yakaball = 0;
        for (int i = 0; i < itemRepository.listAll().size(); i++) {
            if (itemRepository.listAll().get(i).type == ItemType.SCROOGE) {
                scrooge += itemRepository.listAll().get(i).quantity;
            }
            if (itemRepository.listAll().get(i).type == ItemType.YAKABALL) {
                yakaball += itemRepository.listAll().get(i).quantity;
            }
        }
        Item itemScrooge = new Item(ItemType.SCROOGE, scrooge);
        Item itemYakaball = new Item(ItemType.YAKABALL, yakaball);
        List<Item> res = new ArrayList<>();
        res.add(itemYakaball);
        res.add(itemScrooge);
        return res;
    }
}
