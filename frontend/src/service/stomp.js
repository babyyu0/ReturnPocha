import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";

// const sockjs = new SockJS("http://localhost:8080/ws");

let stompClient = null;

const overStomp = () => {
  stompClient = Stomp.over(() => {
    return new SockJS("http://localhost:8080/ws");
  });

  stompClient.debug = () => {};
};

const connectStomp = (callback) => {
  stompClient.connect({}, callback);
};

export { stompClient, overStomp, connectStomp };
