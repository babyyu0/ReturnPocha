import EnterPage from './enter/EnterPage';
import './resource/css/globals.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
      <BrowserRouter>
        <div id="header"></div>
        <Routes>
          <Route path='/' element={ <EnterPage /> } />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
