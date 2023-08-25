// modules
import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useRecoilState, useSetRecoilState } from 'recoil';

// services
import { RoomState } from '../recoil/RoomState';
import { PlayerState, PlayerListState } from '../recoil/PlayerListState';
import { api } from '../service/axios';

// components
import '../resource/css/enter.css';
import NameInput from '../component/NameInput';

function EnterContainer() {

    const navigate = useNavigate();

    const roomId = useParams().roomId;
    let [url, setUrl] = useState(null);
    let data = {};

    const [room, setRoom] = useRecoilState(RoomState);
    const [name, setName] = useState("");  // player 닉네임
    const setPlayer = useSetRecoilState(PlayerState);  // player ID 저장
    const setPlayerList = useSetRecoilState(PlayerListState);  // playerList 불러오기

    const startGame = () => {  // 방 생성/조회 여부 결정
        if (!roomId) {
            setUrl('/room/create');
        } else {
            setUrl('/room/select');
            data = { ...data, 'id': roomId };
        }
    };

    useEffect(() => {  // 방 생성/조회 여부 결정
        if (url) {
            api({ url, data }).then((response) => {
                setRoom({ 'id': response.data.id, progress: false });
            }).catch((error) => {
                console.error(error);
            });
        }
    }, [url]);

    useEffect(() => {  // roomId 받았을 경우 플레이어 생성
        if (room.id) {
            api({
                url: `/player/create`,
                data: {
                    name,
                    'roomId': room.id
                }
            }).then((response) => {
                setPlayer(response.data.id);
                setPlayerList(response.data.playerList);

                navigate(`/room`);
            });
        }
    }, [room]);

    return (
        <div id='enter-container'>
            <div id='enter-contents'>
                <div>
                    <img src='/enter/logo.png' width='500px' alt='로고' />
                </div>
                <div>
                    <NameInput name={name} setName={setName} startGame={startGame} />
                </div>
            </div>
            <img id='underroof' src='/roof.png' alt='지붕' />
        </div>
    );
}

export default EnterContainer;