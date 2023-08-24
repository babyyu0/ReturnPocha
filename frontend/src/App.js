import EnterPage from './enter/EnterPage';
import RoomPage from './room/RoomPage';
import './resource/css/globals.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { RecoilRoot } from 'recoil';

function App() {
  return (
    <RecoilRoot>
      <BrowserRouter>
        <div id="header"></div>
        <Routes>
          <Route path='/' element={<EnterPage />} />
          <Route path='/room' element={<RoomPage />} />
        </Routes>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;
