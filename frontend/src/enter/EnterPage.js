import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../resource/css/enter.css';
import api from '../service/axios';
import NicknameInput from './NicknameInput';
import { useRecoilState } from 'recoil';
import { RoomState } from '../recoil/RoomState';
import { PlayerState } from '../recoil/PlayerState';

function EnterPage() {

    const navigate = useNavigate();
    const [room, setRoom] = useRecoilState(RoomState);
    const [player, setPlayer] = useRecoilState(PlayerState);

    const startGame = () => {  // 게임 시작 (방 만들기)
        api({
            url: `/room/create`
        }).then((response) => {
            setRoom({ "id": response.data.id, "progress": false });
        });
    };

    useEffect(() => {
        if(room.id) {
            navigate(`/room`);
        }
    }, [room]);

    return (
        <div id="enter-container">
            <div id="enter-contents">
                <div>
                    <img src='/enter/logo.png' width="500px" alt="로고" />
                </div>
                <div>
                    <NicknameInput player={player} setPlayer={setPlayer} startGame={startGame} />
                </div>
            </div>
            <img id="underroof" src="/roof.png" alt="지붕" />
        </div>
    );
}

export default EnterPage;