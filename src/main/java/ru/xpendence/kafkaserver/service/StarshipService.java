package ru.xpendence.kafkaserver.service;

import ru.xpendence.kafkaserver.dto.StarshipDto;

public interface StarshipService {
    void send(StarshipDto dto);
}
