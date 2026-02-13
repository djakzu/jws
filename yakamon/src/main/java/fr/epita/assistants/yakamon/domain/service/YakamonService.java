package fr.epita.assistants.yakamon.domain.service;

import fr.epita.assistants.yakamon.data.repository.YakamonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class YakamonService {
    @Inject
    YakamonRepository yakamonRepository;

}
