import { recoilPersist } from 'recoil-persist';
import { atom } from "recoil";

const { persistAtom } = recoilPersist({
    key: 'sessionStorage',
    storage: sessionStorage,
});

export const StompState = atom({
    key: 'StompState',
    default: {},
    effects_UNSTABLE: [persistAtom]
});