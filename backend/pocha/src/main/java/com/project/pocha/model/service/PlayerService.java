package com.project.pocha.model.service;

import com.project.pocha.model.dto.request.PlayerRequestDto;
import com.project.pocha.model.dto.response.SetPlayerResponseDto;
import com.project.pocha.util.exception.SetPlayerException;
import com.project.pocha.util.exception.UpdatePlayerException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PlayerService {
    SetPlayerResponseDto setPlayer(PlayerRequestDto playerRequestDto) throws SetPlayerException;
    Object updatePlayer(Map<String, Object> payload, String roomId) throws UpdatePlayerException;
}