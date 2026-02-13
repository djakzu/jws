package fr.epita.assistants.yakamon.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "yakamon")
public class YakamonModel {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "nickname", length = 20)
    public String nickname;

    @Column(name = "energy_points")
    public Integer energyPoints;

    @ManyToOne
    @JoinColumn(name = "yakadex_entry_id", nullable = false)
    public YakamonModel yakadexEntry;
}
