import { atom } from "recoil";

export const PlayerState = atom({
    key: 'PlayerState',
    default: ''
});

export const PlayerListState = atom({
    key: 'PlayerListState',
    default: {}
});