package com.project.pocha.util.exception;

public class SendChatException extends Exception {
    public SendChatException() {
        super("채팅 전송 실패");
    }
    public SendChatException(String message) {
        super("채팅 전송 실패: " + message);
    }
}
