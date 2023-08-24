import { atom } from "recoil";

export const PlayerState = atom({
    key: 'PlayerState',
    default: {
        id: '',
        name: '',
        head: '',
        ready: ''
    }
});