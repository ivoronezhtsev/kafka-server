package ru.xpendence.kafkaserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.xpendence.kafkaserver.dto.StarshipDto;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StarshipServiceImpl implements StarshipService {

    private final KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(StarshipDto dto) {
        log.info("<= sending {}", writeValueAsString(dto));
        kafkaStarshipTemplate.send("server.starship", dto);
    }

    @KafkaListener(id = "Starship", topics = {"server.starship"}, containerFactory = "singleFactory")
    public void consume(StarshipDto dto) {
        log.info("=> consumed {}", writeValueAsString(dto));
    }

    private String writeValueAsString(StarshipDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
