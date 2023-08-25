package com.project.pocha.model.service;


import com.project.pocha.model.dto.request.RoomRequestDto;
import com.project.pocha.model.dto.response.RoomResponseDto;
import com.project.pocha.util.exception.SendChatException;
import com.project.pocha.util.exception.SetRoomException;

import java.util.Map;

public interface RoomService {
    RoomResponseDto setRoom() throws SetRoomException;

    Map<String, String> sendChat(Map<String, Object> payload, String roomId) throws SendChatException;
}
