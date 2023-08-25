package com.project.pocha.model.service;

import com.project.pocha.model.dto.request.PlayerRequestDto;
import com.project.pocha.util.exception.SetPlayerException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PlayerService {
    Map<String, Object> setPlayer(PlayerRequestDto playerRequestDto) throws SetPlayerException;

}