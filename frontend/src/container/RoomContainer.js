// modules
import { useEffect, useState } from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import Swal from "sweetalert2";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";

// services
import { StompState } from "../recoil/SockState";
import { PlayerListState, PlayerState } from "../recoil/PlayerListState";
import { RoomState } from "../recoil/RoomState";
import { stompClient, overStomp, connectStomp } from "../service/stomp";

// components
import "../resource/css/room.css";
import PlayerCamList from "../component/PlayerCamList";
import ChatInput from "../component/ChatInput";
import ChatHistory from "../component/ChatHistory";

function RoomContainer() {
  const room = useRecoilValue(RoomState);
  const playerId = useRecoilValue(PlayerState);
  const [playerList, setPlayerList] = useRecoilState(PlayerListState);

  const [message, setMessage] = useState("");
  const [chatHistory, setChatHistory] = useState([]);
  const [alertMsg, setAlertMsg] = useState("");

  const send = () => {
    // 채팅 전송
    if (!message) {
      setAlertMsg("채팅이 입력 되지 않았습니다.");
      return;
    }

    if (stompClient.connected) {
      const payload = { playerId, message };
      stompClient.send(`/chat/${room.id}`, {}, JSON.stringify(payload));
    }

    setMessage("");
  };

  const subscribe = () => {
    stompClient.subscribe(`/topic/player/${room.id}`, (response) => {
      console.log("Player STOMP Response : ", response);
    });
    stompClient.subscribe(`/topic/chat/${room.id}`, (response) => {
      const body = JSON.parse(response.body);
      setChatHistory(prevHistory => [...prevHistory, body]);
    });
    stompClient.subscribe(`/queue/${playerId}`, (response) => {
      setAlertMsg(response.body);
    });
  };

  useEffect(() => {
    // Alert 띄우기
    if (alertMsg) {
      Swal.fire({
        icon: "error",
        title: `<span style="font-family: 'NanumSquare'"}">${alertMsg}</span>`,
        showConfirmButton: false,
        timer: 1000,
      });
      setAlertMsg("");
    }
  }, [alertMsg]);

  useEffect(() => {
    if (stompClient && !stompClient.connected) connectStomp(subscribe);
    else if (!stompClient) overStomp();
    else subscribe();
  }, [stompClient]);

  return (
    <div id="room-container">
      <img id="uproof" src="/roof.png" alt="지붕" />
      <div id="room-contents">
        <PlayerCamList />
        <ChatHistory chatHistory={chatHistory} setChatHistory={setChatHistory} />
        <ChatInput message={message} setMessage={setMessage} send={send} />
      </div>
    </div>
  );
}

export default RoomContainer;
