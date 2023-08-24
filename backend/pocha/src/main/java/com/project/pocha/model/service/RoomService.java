package com.project.pocha.model.service;


import com.project.pocha.model.dto.request.RoomRequestDto;
import com.project.pocha.model.dto.response.RoomResponseDto;
import com.project.pocha.util.exception.SetRoomException;

public interface RoomService {
    RoomResponseDto setRoom() throws SetRoomException;
}
