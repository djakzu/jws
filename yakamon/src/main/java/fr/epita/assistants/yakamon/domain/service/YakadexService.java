package fr.epita.assistants.yakamon.domain.service;

import fr.epita.assistants.yakamon.data.model.YakadexEntryModel;
import fr.epita.assistants.yakamon.data.repository.YakadexEntryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class YakadexService {
    @Inject
    YakadexEntryRepository yakadexEntryRepository;

    public List<YakadexEntryModel> getYakadex() {
        List<YakadexEntryModel> res = new ArrayList<>();
        for (int i = 0; i < yakadexEntryRepository.count(); i++) {
            res.add(yakadexEntryRepository.listAll().get(i));
        }
        return res;
    }

    public YakadexEntryModel getYakamon(Integer id) {
        for (int i = 0; i < yakadexEntryRepository.count(); i++) {
            if (Objects.equals(id, yakadexEntryRepository.listAll().get(i).getId())) {
                return yakadexEntryRepository.listAll().get(i);
            }
        }
        return null;
    }
}
