package com.project.pocha.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoomResponseDto {
    private String id;
    private boolean progress;
}
