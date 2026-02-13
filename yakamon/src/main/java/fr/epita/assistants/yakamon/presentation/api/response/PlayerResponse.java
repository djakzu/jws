package fr.epita.assistants.yakamon.presentation.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {
    public String uuid;
    public String name;
    public Integer posX;
    public Integer poY;
    public LocalDateTime lastMove;
    public LocalDateTime lastCollect;
    public LocalDateTime lastCatch;
    public LocalDateTime lastFeed;
}