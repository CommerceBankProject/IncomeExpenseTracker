import RegisterPage from './register.tsx';
import LoginPage from './login';
import DashBoard from './dashBoard';
import React from 'react';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<DashBoard />} />
                <Route path="/create-account" element={<RegisterPage />} />
                <Route path="/dashBoard/:userId" element={<DashBoard />} />
            </Routes>
        </Router>
);
}

export default App;