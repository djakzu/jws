package fr.epita.assistants.yakamon.presentation.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class YakadexResponse {
    List<YakadexEntryResponse> yakadex;
}
