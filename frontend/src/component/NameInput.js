function NameInputComponent(props) {
    const name = props.name;
    const setName = props.setName;
    const startGame = props.startGame;

    return (
        <div id="name-container">
            <input id="name-input" value={name} onChange={(e) => setName(e.target.value) } maxLength={10} placeholder='닉네임 입력' />
            <button id="name-button" onClick={startGame}>시작</button>
        </div>
    );
}

export default NameInputComponent;