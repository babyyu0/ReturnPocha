package com.project.pocha.util.exception;

public class SetPlayerException extends Exception {
    public SetPlayerException() {
        super("플레이어 생성 실패");
    }
    public SetPlayerException(String message) {
        super("플레이어 생성 실패: " + message);
    }
}
