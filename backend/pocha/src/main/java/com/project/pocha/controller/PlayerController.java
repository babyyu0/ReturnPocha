package com.project.pocha.controller;

import com.project.pocha.model.dto.request.PlayerRequestDto;
import com.project.pocha.model.dto.response.PlayerResponseDto;
import com.project.pocha.model.service.PlayerService;
import com.project.pocha.util.exception.SetPlayerException;
import com.project.pocha.util.exception.UpdatePlayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("player")
public class PlayerController {
    private final SimpMessagingTemplate webSocket;

    private final PlayerService playerService;

    @Autowired
    private PlayerController(SimpMessagingTemplate webSocket, PlayerService playerService) {
        this.webSocket = webSocket;
        this.playerService = playerService;
    }

    @PostMapping("create")
    public ResponseEntity<?> setPlayer(@RequestBody PlayerRequestDto playerRequestDto) {
        try {
            return ResponseEntity.ok(playerService.setPlayer(playerRequestDto));
        } catch(SetPlayerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @MessageMapping("/player/update")
    public void updatePlayer(@Payload Map<String, Object> payload, @DestinationVariable String roomId) {
        try {
            webSocket.convertAndSend("/topic/player/" + roomId, playerService.updatePlayer(payload, roomId));
        } catch(UpdatePlayerException e) {
            webSocket.convertAndSend("/queue/" + payload.get("id"), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            webSocket.convertAndSend(webSocket.getUserDestinationPrefix(), e.getMessage());
        }
    }
}
