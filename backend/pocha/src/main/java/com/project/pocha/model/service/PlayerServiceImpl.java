package com.project.pocha.model.service;

import com.project.pocha.model.dto.request.PlayerRequestDto;
import com.project.pocha.model.dto.response.PlayerResponseDto;
import com.project.pocha.model.entity.Player;
import com.project.pocha.model.entity.Room;
import com.project.pocha.model.repository.PlayerRepository;
import com.project.pocha.model.repository.RoomRepository;
import com.project.pocha.util.exception.SetPlayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Map<String, Object> setPlayer(PlayerRequestDto playerRequestDto) throws SetPlayerException {
        Room room = roomRepository.findById(playerRequestDto.getRoomId()).orElseThrow(() -> new SetPlayerException("방 ID 조회 실패"));

        List<Player> playerList = new ArrayList<>();
        boolean head = false;
        if (room.getPlayerList() == null || room.getPlayerList().size() == 0) {
            playerList = new ArrayList<>();
            head = true;
        } else {
            playerList = room.getPlayerList();
        }

        Player player = Player.builder()
                .id(UUID.randomUUID().toString())  // 랜덤 UUID 생성
                .name(playerRequestDto.getName())
                .room(roomRepository.findById(playerRequestDto.getRoomId()).orElseThrow(() -> new SetPlayerException("방 ID 조회 실패")))
                .head(head)
                .ready(false)
                .build();

        try {
            playerRepository.save(player);
            playerList.add(player);  // Player List에 접속 Player 추가
            room = Room.builder()
                    .id(room.getId())
                    .playerList(playerList)
                    .build();
            roomRepository.save(room);

            Map<String, PlayerResponseDto> playerResponseDtoMap = new HashMap<>();
            Map<String, Object> responsePayload = new HashMap<>();
            for(Player p : playerList) {
                playerResponseDtoMap.put(p.getId(), PlayerResponseDto.builder()
                        .id(player.getId())
                        .name(player.getName())
                        .head(player.isHead())
                        .ready(player.isReady())
                        .build()
                );
            }

            responsePayload.put("id", player.getId());
            responsePayload.put("playerList", playerResponseDtoMap);

            return responsePayload;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SetPlayerException();
        }
    }
}