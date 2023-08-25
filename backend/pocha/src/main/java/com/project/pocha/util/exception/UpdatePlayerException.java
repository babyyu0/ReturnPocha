package com.project.pocha.util.exception;

public class UpdatePlayerException extends Exception {
    public UpdatePlayerException() {
        super("플레이어 수정 실패");
    }
    public UpdatePlayerException(String message) {
        super("플레이어 수정 실패: " + message);
    }
}
