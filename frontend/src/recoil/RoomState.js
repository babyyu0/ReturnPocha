import { recoilPersist } from 'recoil-persist';
import { atom } from "recoil";

const { persistAtom } = recoilPersist({
    key: 'sessionStorage',
    storage: sessionStorage,
  });

export const RoomState = atom({
    key: 'RoomState',
    default: {
        id: '',
        progress: false
    },
    effects_UNSTABLE: [persistAtom]
});