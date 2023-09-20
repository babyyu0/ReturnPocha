package com.project.pocha.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(timeToLive = 600L)
@Builder
@Getter
public class Player {
    @Id
    private String id;
    private String name;
    private boolean head;
    private boolean ready;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id", nullable = false)
    private Room room;
}
