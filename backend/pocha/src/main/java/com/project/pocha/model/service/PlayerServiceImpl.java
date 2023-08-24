package com.project.pocha.model.service;

import com.project.pocha.model.dto.request.PlayerRequestDto;
import com.project.pocha.model.dto.response.PlayerResponseDto;
import com.project.pocha.model.entity.Player;
import com.project.pocha.model.repository.PlayerRepository;
import com.project.pocha.model.repository.RoomRepository;
import com.project.pocha.util.exception.SetPlayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final RoomRepository roomRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    private PlayerServiceImpl(RoomRepository roomRepository, PlayerRepository playerRepository) {
        this.roomRepository = roomRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerResponseDto setPlayer(PlayerRequestDto playerRequestDto) throws SetPlayerException {
        Player player = Player.builder()
                .id(UUID.randomUUID().toString())  // 랜덤 UUID 생성
                .name(playerRequestDto.getName())
                .room(roomRepository.findById(playerRequestDto.getRoomId()).orElseThrow(() -> new SetPlayerException("방 ID 조회 실패")))
                .build();

        try {
            playerRepository.save(player);

            return PlayerResponseDto.builder()
                    .id(player.getId())
                    .name(player.getName())
                    .roomId(player.getRoom().getId())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SetPlayerException();
        }
    }
}