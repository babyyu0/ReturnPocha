package com.project.pocha.model.service;

import com.project.pocha.model.dto.request.RoomRequestDto;
import com.project.pocha.model.dto.response.RoomResponseDto;
import com.project.pocha.model.entity.Player;
import com.project.pocha.model.entity.Room;
import com.project.pocha.model.repository.PlayerRepository;
import com.project.pocha.model.repository.RoomRepository;
import com.project.pocha.util.exception.SendChatException;
import com.project.pocha.util.exception.SetRoomException;
import com.project.pocha.util.exception.UpdatePlayerException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final PlayerRepository playerRepository;

    private RoomServiceImpl(RoomRepository roomRepository, PlayerRepository playerRepository) {
        this.roomRepository = roomRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public RoomResponseDto setRoom() throws SetRoomException {
        Room room = Room.builder()
                .id(UUID.randomUUID().toString())  // 랜덤 UUID 생성
                .progress(false)
                .build();

        try {
            roomRepository.save(room);

            return RoomResponseDto.builder()
                    .id(room.getId())
                    .progress(room.isProgress())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SetRoomException();
        }
    }

    @Override
    public Map<String, String> sendChat(Map<String, Object> payload, String roomId) throws SendChatException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new SendChatException("방 ID 조회에 실패 했습니다."));
        Player player = playerRepository.findById((String) payload.get("playerId")).orElseThrow(() -> new SendChatException("플레이어 ID 조회에 실패 했습니다."));

        if (!player.getRoom().getId().equals(roomId)) {
            throw new SendChatException("현재 방 인원이 아닙니다.");
        }
        if (!payload.containsKey("message")) {
            throw new SendChatException("메시지가 입력 되지 않았습니다.");
        }

        // 날짜 받아오기
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        Map<String, String> responsePayload = new HashMap<>();
        responsePayload.put("name", player.getName());
        responsePayload.put("time", time.format(formatter));
        responsePayload.put("message", (String) payload.get("message"));

        return responsePayload;
    }
}
