import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";

const sockjs = new SockJS('http://localhost:8080/ws');

const connect = () => {
    console.log("a");
    return Stomp.over(sockjs);
}

export { connect };
