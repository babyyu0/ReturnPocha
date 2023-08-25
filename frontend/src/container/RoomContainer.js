import '../resource/css/room.css'
import PlayerCamList from '../component/PlayerCamList';
import ChatInput from '../component/ChatInput';
import { useState } from 'react';
import ModalComponent from '../common/ModalComponent';

function RoomContainer() {

    const [chat, setChat] = useState("");
    const [modal, setModal] = useState(false);
    const [modalMsg, setModalMsg] = useState("");

    const send = () => {
        if (!chat) {
            setModal(true);
            setModalMsg("채팅이 입력 되지 않았습니다.");
            return;
        }
        console.log(chat);
        setChat("");
    };

    return (
        <div id='room-container'>
            <ModalComponent message={modalMsg} modal={modal} setModal={setModal} />
            <img id='uproof' src='/roof.png' alt='지붕' />
            <div id='room-contents'>
                <PlayerCamList />
                <ChatInput chat={chat} setChat={setChat} send={send} />
            </div>
        </div>
    );
}

export default RoomContainer;