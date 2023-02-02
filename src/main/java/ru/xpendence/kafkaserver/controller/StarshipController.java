package ru.xpendence.kafkaserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xpendence.kafkaserver.dto.StarshipDto;
import ru.xpendence.kafkaserver.service.StarshipService;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 12.02.19
 * Time: 19:02
 * e-mail: vyacheslav.chernyshov@stoloto.ru
 */
@RestController
@RequestMapping("/starship")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StarshipController {

    private final StarshipService service;

    @PostMapping
    public void send(@RequestBody StarshipDto dto) {
        service.send(dto);
    }
}
