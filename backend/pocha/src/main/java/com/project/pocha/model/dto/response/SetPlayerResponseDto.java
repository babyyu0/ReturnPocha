package com.project.pocha.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class SetPlayerResponseDto {
    private String id;
    private Map<String, PlayerResponseDto> playerMap;
}
