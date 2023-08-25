import '../resource/css/room.css'
import PlayerCamList from '../component/PlayerCamList';
import ChatInput from '../component/ChatInput';
import { useEffect, useState } from 'react';
import ChatArea from '../component/ChatArea';
import { useRecoilState, useRecoilValue } from 'recoil';
import { PlayerListState, PlayerState } from '../recoil/PlayerListState';
import Swal from 'sweetalert2';
import { RoomState } from '../recoil/RoomState';
import { connect } from '../service/stomp';

function RoomContainer() {

    const room = useRecoilValue(RoomState);
    const playerId = useRecoilValue(PlayerState);
    const [playerList, setPlayerList] = useRecoilState(PlayerListState);

    const [stomp, setStomp] = useState(null);
    const [chat, setChat] = useState("");
    const [chatArea, setChatArea] = useState([]);
    const [alertMsg, setAlertMsg] = useState("");

    const send = () => {
        if (!chat) {
            setAlertMsg("채팅이 입력 되지 않았습니다.");
            return;
        }
        const changeArea = [...chatArea, { "name": playerList[playerId].name, "time": "13:05", "message": chat }];
        setChatArea(changeArea);
        setChat("");
    };

    useEffect(() => {
        if (alertMsg) {
            Swal.fire({
                icon: 'error',
                title: `<span style="font-family: 'NanumSquare'"}">${alertMsg}</span>`,
                showConfirmButton: false,
                timer: 1000
            });
            setAlertMsg('');
        }
    }, [alertMsg]);

    useEffect(() => {
        if(!stomp) {
            setStomp(connect());
            return;
        }
        stomp.connect(() => {
            stomp.subscribe(`/topic/chat/${room.id}`, (response) => {
            });

        });
    }, [stomp]);
    return (
        <div id='room-container'>
            <img id='uproof' src='/roof.png' alt='지붕' />
            <div id='room-contents'>
                <PlayerCamList />
                <ChatArea chatArea={chatArea} setChatArea={setChatArea} />
                <ChatInput chat={chat} setChat={setChat} send={send} />
            </div>
        </div>
    );
}

export default RoomContainer;