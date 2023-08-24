package com.project.pocha.util.exception;

public class SetRoomException extends Exception {
    public SetRoomException() {
        super("방 생성 실패");
    }
    public SetRoomException(String message) {
        super("방 생성 실패: " + message);
    }
}
