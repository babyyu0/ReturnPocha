import { recoilPersist } from 'recoil-persist';
import { atom } from "recoil";

const { persistAtom } = recoilPersist({
    key: 'sessionStorage',
    storage: sessionStorage,
  });

export const PlayerState = atom({
    key: 'PlayerState',
    default: '',
    effects_UNSTABLE: [persistAtom]
});

export const PlayerListState = atom({
    key: 'PlayerListState',
    default: {},
    effects_UNSTABLE: [persistAtom]
});