import RegisterPage from './register';
import LoginPage from './login';
import DashBoard from './dashBoard';
import React from 'react';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';


function App(){

  return (
    <Router>
      <Routes>
        <Route path="/" element={<DashBoard />} />
        <Route path="/user_register" element={<RegisterPage />} />
        <Route path="/home" element={<DashBoard />} />
      </Routes>

    </Router>
  )
}

export default App;