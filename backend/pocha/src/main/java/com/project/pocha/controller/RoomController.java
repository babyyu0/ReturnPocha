package com.project.pocha.controller;

import com.project.pocha.model.service.RoomService;
import com.project.pocha.util.exception.SetRoomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
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

}
