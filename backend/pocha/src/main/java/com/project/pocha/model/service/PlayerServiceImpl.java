package com.project.pocha.model.service;

import com.project.pocha.model.dto.request.PlayerRequestDto;
import com.project.pocha.model.dto.response.PlayerResponseDto;
import com.project.pocha.model.dto.response.SetPlayerResponseDto;
import com.project.pocha.model.entity.Player;
import com.project.pocha.model.entity.Room;
import com.project.pocha.model.repository.PlayerRepository;
import com.project.pocha.model.repository.RoomRepository;
import com.project.pocha.util.exception.SetPlayerException;
import com.project.pocha.util.exception.UpdatePlayerException;
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
    public SetPlayerResponseDto setPlayer(PlayerRequestDto playerRequestDto) throws SetPlayerException {
        System.out.println("Set Player Service 호출");
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
            for (Player p : playerList) {
                playerResponseDtoMap.put(p.getId(), PlayerResponseDto.builder()
                        .name(p.getName())
                        .head(p.isHead())
                        .ready(p.isReady())
                        .build()
                );
            }
            System.out.println(playerResponseDtoMap);

            return SetPlayerResponseDto.builder()
                    .id(player.getId())
                    .playerMap(playerResponseDtoMap)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SetPlayerException();
        }
    }

    @Override
    public Object updatePlayer(Map<String, Object> payload, String roomId) throws UpdatePlayerException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new UpdatePlayerException("방 ID 조회 실패"));
        Player player = playerRepository.findById((String) payload.get("id")).orElseThrow(() -> new UpdatePlayerException("플레이어 ID 조회 실패"));

        boolean head = false;
        if (room.getPlayerList() == null || room.getPlayerList().size() == 0) head = true;

        if (!player.getRoom().getId().equals(roomId)) {
            throw new UpdatePlayerException("현재 방 인원이 아닙니다.");
        }

        player = Player.builder()
                .id(player.getId())
                .name(player.getName())
                .head(head)
                .ready((boolean) payload.get("ready"))
                .build();

        playerRepository.save(player);

        return PlayerResponseDto.builder()
                // .id(player.getId())
                .name(player.getName())
                .head(player.isHead())
                .ready(player.isReady())
                .build();
    }
}