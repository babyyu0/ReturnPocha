import '../resource/css/enter.css';

function EnterPage() {
    return (
        <div id="enter-container">
            <div id="enter-contents">
                <div>
                    <img src='/enter/logo.png' width="500px" />
                </div>
                <div>
                    <div id="nickname-container">
                        <input id="nickname-input" maxLength={10} placeholder='닉네임 입력' />
                        <button id="nickname-button">시작</button>
                    </div>
                </div>
            </div>
            <img id="underroof" src="/enter/roof.png" />
        </div>
    );
}

export default EnterPage;