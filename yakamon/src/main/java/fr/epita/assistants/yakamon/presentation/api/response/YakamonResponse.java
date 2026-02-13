package fr.epita.assistants.yakamon.presentation.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YakamonResponse {
    public String uuid;
    public String nickname;
    public Integer yakadexId;
    public Integer energyPoints;
}
