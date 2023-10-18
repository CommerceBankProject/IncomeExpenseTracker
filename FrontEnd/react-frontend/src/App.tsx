import RegisterPage from './register';
import LoginPage from './login';
import DashBoard from './dashBoard';
import React from 'react';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';


function App(){

  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/create-account" element={<RegisterPage />} />
      </Routes>

    </Router>
  )
}

export default App;