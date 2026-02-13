package fr.epita.assistants.yakamon.data.model;

import fr.epita.assistants.yakamon.utils.tile.YakamonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "yakadex_entry")
public class YakadexEntryModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 20)
    public String name;

    @Column(name = "first_type")
    @Enumerated(EnumType.STRING)
    public YakamonType firstType;

    @Column(name = "second_type")
    @Enumerated(EnumType.STRING)
    public YakamonType secondType;

    @Column(name = "description")
    public String description;

    @OneToOne
    @JoinColumn(name = "evolution_id")
    public YakadexEntryModel evolution;

    @Column(name = "evolve_threshold")
    public Integer evolveThreshold;

    @Column(name = "caught")
    public Boolean caught;


}
