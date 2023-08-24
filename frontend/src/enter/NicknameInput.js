function NicknameInputComponent(props) {
    const player = props.player;
    const setPlayer = props.setPlayer;
    const startGame = props.startGame;

    return (
        <div id="nickname-container">
            <input id="nickname-input" value={player.name} onChange={(e) => setPlayer({...player, "name": e.target.value}) } maxLength={10} placeholder='닉네임 입력' />
            <button id="nickname-button" onClick={startGame}>시작</button>
        </div>
    );
}

export default NicknameInputComponent;