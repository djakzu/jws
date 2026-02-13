package fr.epita.assistants.yakamon.converter;

import fr.epita.assistants.yakamon.data.model.YakadexEntryModel;
import fr.epita.assistants.yakamon.presentation.api.response.YakadexEntryResponse;
import fr.epita.assistants.yakamon.presentation.api.response.YakadexResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class YakadexConverter {
    @Inject
    YakadexEntryConverter yakadexEntryConverter;
    public YakadexResponse toResponse(List<YakadexEntryModel> yakadexEntryModels) {
        List<YakadexEntryResponse> yakadexEntryResponses = new ArrayList<>();
        for (int i = 0; i < yakadexEntryModels.size(); i++) {
            yakadexEntryResponses.add(yakadexEntryConverter.toResponse(yakadexEntryModels.get(i)));
        }
        return new YakadexResponse(yakadexEntryResponses);
    }
}
