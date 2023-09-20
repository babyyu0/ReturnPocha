package com.project.pocha.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PlayerResponseDto {
    // private String uuid;
    private String name;
    private boolean head;
    private boolean ready;
}
