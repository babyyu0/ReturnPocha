package com.project.pocha.model.service;

import com.project.pocha.model.dto.request.RoomRequestDto;
import com.project.pocha.model.dto.response.RoomResponseDto;
import com.project.pocha.model.entity.Room;
import com.project.pocha.model.repository.RoomRepository;
import com.project.pocha.util.exception.SetRoomException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
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
}
