import { useEffect, useRef } from "react";

function ChatHistory(props) {
  const chatHistory = props.chatHistory;
  const scrollRef = useRef();

  useEffect(() => {
    scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
  }, [chatHistory]);

  return (
    <div id="chat-container" ref={scrollRef}>
      {chatHistory
        ? chatHistory.map((chat, index) => (
            <div className="chat-content" key={index}>
              <div className="name">{chat.name}</div>
              <div className="time">{chat.time}</div>
              <div className="message">{chat.message}</div>
            </div>
          ))
        : null}
    </div>
  );
}

export default ChatHistory;
