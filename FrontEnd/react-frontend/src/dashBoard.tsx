import React, { useEffect, useState } from "react";
import BalancePage from "./BalancePage"; // Ensure this import is correct
import "./DashBoard.css";
import 'bootstrap-icons/font/bootstrap-icons.css';
import axios from "axios";
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';

function DashBoard() {
    const [activeComponent, setActiveComponent] = useState<'balancepage' | 'profile'>('balancepage');
    const { userId } = useParams<{ userId: string }>();

    const renderComponent = () => {
        if (activeComponent === 'balancepage') {
            // Pass userId as a prop to BalancePage
            return <BalancePage userId={userId} />;
        } else {
            return null;
        }
    };

    const [userName, setUserName] = useState<string>('');

    useEffect(() => {
        axios.get(`http://localhost:8081/user_accounts/${userId}`)
            .then((response) => {
                const { firstName, lastName } = response.data;
                setUserName(`${firstName} ${lastName}`);
            })
            .catch((error) => {
                console.error('API request error', error);
            });
    }, [userId]);


    return(
        <div className="dashboard-container">
            <div className="sidebar">
                <div className="logo"></div>
                <ul className="menu">
                    <li className="activity">
                        <Link to="#" onClick={() => setActiveComponent('balancepage')}>
                            <i className="bi bi-bank"></i>
                            <span>Dashboard</span>
                        </Link>
                    </li>

                    <li>
                        <Link to="/profile">
                            <i className="bi bi-person-lines-fill"></i>
                            <span>Profile</span>
                        </Link>
                    </li>

                    <li>
                        <Link to="/statistics">
                            <i className="bi bi-card-list"></i>
                            <span>Statistic</span>
                        </Link>
                    </li>

                    <li className="logout">
                        <Link to="/">
                            <i className="bi bi-door-closed"></i>
                            <span>Logout</span>
                        </Link>
                    </li>
                </ul>
            </div>

            <div className="main--content">
                <div className="header--wrapper">
                    <div className="header--title">
                        <span>Welcome</span>
                        <h2 id="userName">{userName}</h2>
                    </div>
                    <div className="user--infor">
                        <div className="search--box">
                    
                        <input type="text" placeholder="Search" className="searchBox"/>
                    </div>
                    <button type="submit" >Search</button>
                </div>
            </div>
            {renderComponent()}  
        </div>
        </div>
    );
}

export default DashBoard;


