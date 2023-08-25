import EnterContainer from './container/EnterContainer';
import RoomContainer from './container/RoomContainer';
import './resource/css/globals.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { RecoilRoot } from 'recoil';

function App() {
  return (
    <RecoilRoot>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<EnterContainer />} />
          <Route path='/:roomId' element={<EnterContainer />} />
          <Route path='/room' element={<RoomContainer />} />
        </Routes>
      </BrowserRouter>
    </RecoilRoot>

  );
}

export default App;
