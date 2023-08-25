import '../resource/css/room.css'
import PlayerCamList from '../component/PlayerCamList';
import ChatInput from '../component/ChatInput';
import { useState } from 'react';
import ModalComponent from '../common/ModalComponent';
import ChatArea from '../component/ChatArea';
import { useRecoilState, useRecoilValue } from 'recoil';
import { PlayerListState, PlayerState } from '../recoil/PlayerListState';

function RoomContainer() {

    const playerId = useRecoilValue(PlayerState);
    const [playerList, setPlayerList] = useRecoilState(PlayerListState);

    const [chat, setChat] = useState("");
    const [chatArea, setChatArea] = useState([]);
    const [modal, setModal] = useState(false);
    const [modalMsg, setModalMsg] = useState("");

    const send = () => {
        if (!chat) {
            setModal(true);
            setModalMsg("채팅이 입력 되지 않았습니다.");
            return;
        }
        const changeArea = [...chatArea, {"name": playerList[playerId].name, "time": "13:05", "message": chat}];
        setChatArea(changeArea);
        setChat("");
    };

    return (
        <div id='room-container'>
            <ModalComponent message={modalMsg} modal={modal} setModal={setModal} />
            <img id='uproof' src='/roof.png' alt='지붕' />
            <div id='room-contents'>
                <PlayerCamList />
                <ChatArea chatArea={chatArea} setChatArea={setChatArea}/>
                <ChatInput chat={chat} setChat={setChat} send={send} />
            </div>
        </div>
    );
}

export default RoomContainer;