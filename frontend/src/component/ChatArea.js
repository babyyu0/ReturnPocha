import { useEffect, useRef } from "react";

function ChatArea(props) {

    const chatArea = props.chatArea;
    const scrollRef = useRef();

    useEffect(() => {
        scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
    }, [chatArea]);

    return (
        <div id='chat-area-container' ref={scrollRef}>
            {
                (chatArea) ?
                    chatArea.map((chat, index) => (
                        <div className='chat-area-content' key={index}>
                            <div className='name'>{chat.name}</div><div className='time'>{chat.time}</div>
                            <div className='message'>{chat.message}</div>
                        </div>
                    ))
                    : null
            }

        </div>
    );
}

export default ChatArea;