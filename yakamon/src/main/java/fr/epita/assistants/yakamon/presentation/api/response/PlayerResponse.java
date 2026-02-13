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
    public String name;
    public Integer x;
    public Integer y;
    public LocalDateTime lastMove;
    public LocalDateTime lastCatch;
    public LocalDateTime lastCollect;
    public LocalDateTime lastFeed;
}