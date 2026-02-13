package fr.epita.assistants.yakamon.presentation.api.response;

import fr.epita.assistants.yakamon.data.model.YakadexEntryModel;
import fr.epita.assistants.yakamon.utils.ElementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class YakadexEntryResponse {
    public Integer id;
    public String name;
    public ElementType firstType;
    public ElementType secondType;
    public String description;
    public YakadexEntryModel evolution;
    public Integer evolveThreshold;
    public Boolean caught;
}
