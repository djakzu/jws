package fr.epita.assistants.yakamon.domain.entity;

import fr.epita.assistants.yakamon.utils.tile.YakamonType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class YakadexEntryEntity {
    public Integer id;
    public String name;
    public YakamonType firstType;
    public YakamonType secondType;
    public String description;
    public Integer evolutionId;
    public Integer evolveThreshold;
    public Boolean caught;
}