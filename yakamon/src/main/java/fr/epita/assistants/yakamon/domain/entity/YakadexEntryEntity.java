package fr.epita.assistants.yakamon.domain.entity;

import fr.epita.assistants.yakamon.utils.tile.YakamonType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class YakadexEntryEntity {
    public Integer id;
    public String name;
    public YakamonType firstType;
    public YakamonType secondType;
    public Integer evolveThreshold;
    public Integer evolutionId;
    public boolean caught;
    public String description;
}