import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
    header: {
      Accept: "application/json",
      "Content-type": "application/json;charset=UTF-8",
    },
    method: "POST"
});

export default api;
