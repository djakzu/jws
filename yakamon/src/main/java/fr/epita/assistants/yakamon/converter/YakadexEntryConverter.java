package fr.epita.assistants.yakamon.converter;

import fr.epita.assistants.yakamon.data.model.YakadexEntryModel;
import fr.epita.assistants.yakamon.presentation.api.response.YakadexEntryResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class YakadexEntryConverter {
    public YakadexEntryResponse toResponse(YakadexEntryModel model) {
        return new YakadexEntryResponse(model.getId(), model.getName(), model.getFirstType(), model.getSecondType(), model.getDescription(), model.getEvolution(), model.getEvolveThreshold(), model.getCaught());
    }
}
