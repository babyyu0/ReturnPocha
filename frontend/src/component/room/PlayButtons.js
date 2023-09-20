import { useEffect } from "react";

function PlayButtons(props) {
  const player = props.player;
  const gamemode = props.gamemode;

  useEffect(() => {
    console.log(player);
  });

  return (
    <div id="btns-container">
      <div>
        <button id="gamemode-btn">{gamemode === "mini" ? "미니게임" : "술게임"} 모드</button>
      </div>
      <div>
        <button className="custom-button">게임 초대</button>
        <button className="custom-button">{(player.head)?"시작":"준비"}</button>
      </div>
    </div>
  );
}

export default PlayButtons;
