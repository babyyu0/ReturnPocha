function ChatInput(props) {
  const message = props.message;
  const setMessage = props.setMessage;
  const send = props.send;

  return (
    <div id='message-container'>
      <input
        id='message-input'
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        onKeyDown={(e) => {
          if (e.key === 'Enter') send();
        }}
        placeholder='채팅 입력'
      />
      <button id='message-button' onClick={send}>
        전송
      </button>
    </div>
  );
}

export default ChatInput;
