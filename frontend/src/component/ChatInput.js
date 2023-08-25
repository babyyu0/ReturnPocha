function ChatInput(props) {
    const chat = props.chat;
    const setChat = props.setChat;
    const send = props.send;

    return (
        <div id='chat-container'>
            <input id='chat-input'
                value={chat}
                onChange={(e) => setChat(e.target.value)}
                onKeyDown={(e) => { if (e.key == 'Enter') send(); }}
                placeholder='채팅 입력' />
            <button id='chat-button' onClick={send}>전송</button>
        </div>
    );
}

export default ChatInput;