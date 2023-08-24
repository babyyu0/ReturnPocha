package com.project.pocha.controller;

import com.project.pocha.model.dto.request.PlayerRequestDto;
import com.project.pocha.model.service.PlayerService;
import com.project.pocha.util.exception.SetPlayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    private PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<?> setPlayer(@RequestBody PlayerRequestDto playerRequestDto) {
        try {
            return ResponseEntity.ok(playerService.setPlayer(playerRequestDto));
        } catch(SetPlayerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
