package com.project.pocha.model.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash
@Builder
@Getter
public class Room {
    @Id
    private String id;

    private boolean process;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Player> playerList = new ArrayList<>();
}