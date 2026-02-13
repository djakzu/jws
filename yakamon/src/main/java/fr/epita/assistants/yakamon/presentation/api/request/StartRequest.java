package fr.epita.assistants.yakamon.presentation.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StartRequest {
    String mapPath;
    String playerName;
}
