package com.project.pocha.model.service;

import com.project.pocha.model.dto.request.PlayerRequestDto;
import com.project.pocha.model.dto.response.PlayerResponseDto;
import com.project.pocha.util.exception.SetPlayerException;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    PlayerResponseDto setPlayer(PlayerRequestDto playerRequestDto) throws SetPlayerException;

}