import { useState } from 'react';
import '../resource/css/enter.css';
import api from '../service/axios';
import NicknameInput from './NicknameInput';

function EnterPage() {

    const [nickname, setNickName] = useState("");

    const setRoom = () => {
        api({}).then((response) => {
            console.log(response.data);
        });
    };

    return (
        <div id="enter-container">
            <div id="enter-contents">
                <div>
                    <img src='/enter/logo.png' width="500px" />
                </div>
                <div>
                    <NicknameInput nickname={nickname} setNickName={setNickName} setRoom={setRoom} />
                </div>
            </div>
            <img id="underroof" src="/roof.png" />
        </div>
    );
}

export default EnterPage;