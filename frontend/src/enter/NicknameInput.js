function NicknameInputComponent(props) {
    const nickname = props.nickname;
    const setNickname = props.setNickname;
    const setRoom = props.setRoom;

    return (
        <div id="nickname-container">
            <input id="nickname-input" value={nickname} onChange={(e) => { setNickname(e.target.value) }} maxLength={10} placeholder='닉네임 입력' />
            <button id="nickname-button" onClick={setRoom}>시작</button>
        </div>
    );
}

export default NicknameInputComponent;