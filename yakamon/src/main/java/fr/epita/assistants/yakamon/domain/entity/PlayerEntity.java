package fr.epita.assistants.yakamon.domain.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class PlayerEntity {
    public String name;
    public Integer x;
    public Integer y;
    public LocalDateTime lastMove;
    public LocalDateTime lastCatch;
    public LocalDateTime lastCollect;
    public LocalDateTime lastFeed;
}