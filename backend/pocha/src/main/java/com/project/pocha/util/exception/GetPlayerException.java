package com.project.pocha.util.exception;

public class GetPlayerException extends Exception {
    public GetPlayerException() {
        super("플레이어 조회 실패");
    }
    public GetPlayerException(String message) {
        super("플레이어 조회 실패: " + message);
    }
}
