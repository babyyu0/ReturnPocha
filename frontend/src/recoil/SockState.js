import { recoilPersist } from 'recoil-persist';
import { atom } from "recoil";

const { persistAtom } = recoilPersist({
    key: 'sessionStorage',
    storage: sessionStorage,
});

export const SockState = atom({
    key: 'SockState',
    default: {},
    effects_UNSTABLE: [persistAtom]
});