package com.project.pocha.controller;

import com.project.pocha.model.dto.response.PlayerResponseDto;
import com.project.pocha.model.service.RoomService;
import com.project.pocha.util.exception.SendChatException;
import com.project.pocha.util.exception.SetRoomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("room")
public class RoomController {
    private final SimpMessagingTemplate webSocket;
    private final RoomService roomService;

    private RoomController(SimpMessagingTemplate webSocket, RoomService roomService) {
        this.webSocket = webSocket;
        this.roomService = roomService;
    }

    @PostMapping("create")
    public ResponseEntity<?> setRoom() {
        try {
            return ResponseEntity.ok(roomService.setRoom());
        } catch(SetRoomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @MessageMapping("/chat/{roomId}")
    public void sendChat(@Payload Map<String, Object> payload, @DestinationVariable String roomId) {
        try {
            System.out.println("/topic/chat/" + roomId);
            webSocket.convertAndSend("/topic/chat/" + roomId, roomService.sendChat(payload, roomId));
        } catch(SendChatException e) {
            webSocket.convertAndSend("/queue/" + payload.get("playerId"), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            webSocket.convertAndSend(webSocket.getUserDestinationPrefix(), e.getMessage());
        }
    }

}
