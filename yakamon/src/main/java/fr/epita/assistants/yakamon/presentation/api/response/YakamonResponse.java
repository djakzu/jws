package fr.epita.assistants.yakamon.presentation.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class YakamonResponse {
    String uuid;
    String nickname;
    Integer yakadexId;
    Integer energyPoints;
}
