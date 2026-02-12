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
    @Id
    public String name;

    @Column(name = "pos_x")
    public Integer posX;

    @Column(name = "pos_y")
    public Integer posY;

    @Column(name = "last_move")
    public LocalDateTime lastMove;
}