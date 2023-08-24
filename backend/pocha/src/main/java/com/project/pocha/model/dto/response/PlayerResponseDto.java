package com.project.pocha.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerResponseDto {
    private String id;
    private String name;
    private String roomId;
}
