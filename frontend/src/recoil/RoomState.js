import { atom } from "recoil";

export const RoomState = atom({
    key: 'RoomState',
    default: {
        id: '',
        progress: false
    }
});