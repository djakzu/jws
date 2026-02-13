package fr.epita.assistants.yakamon.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "player")
public class PlayerModel {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "name", length = 20)
    public String name;

    @Column(name = "pos_x")
    public Integer posX;

    @Column(name = "pos_y")
    public Integer posY;

    @Column(name = "last_move")
    public LocalDateTime lastMove;

    @Column(name = "last_catch")
    public LocalDateTime lastCatch;

    @Column(name = "last_collect")
    public LocalDateTime lastCollect;

    @Column(name = "last_feed")
    public LocalDateTime lastFeed;
}