import { useEffect,useState } from "react";
import BalancePage from "./balancePage";
import UserProfile from "./UserProfile";
import UserStatistic from "./statisticPage";
import "./dashBoard.css"
import 'bootstrap-icons/font/bootstrap-icons.css';
import axios from "axios";
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';


function DashBoard() {
    const [activeComponent, setActiveComponent] = useState<'balancepage' | 'profile' | 'statistic'>('balancepage');
    const { userId } = useParams<{ userId: string }>();


        const renderComponent = () => {
        if (activeComponent === 'balancepage'){
            return <BalancePage />;
        }

        else if (activeComponent === 'profile')
        {
            return <UserProfile />;
        }

        else if (activeComponent === 'statistic')
        {
            return <UserStatistic />;
        }
    }

    const [userName, setUserName] = useState<String>('');
    useEffect(() => {
        // Use the provided API endpoint with the userId
        axios.get(`http://localhost:8081/user_accounts/${userId}`)
        .then((response) => {
        // Extract firstName and lastName from the response
            const { firstName, lastName } = response.data;
            // Set the userName state as the combination of firstName and lastName
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
                        <a href="#" onClick={() => setActiveComponent('balancepage')}>
                            <i className="bi bi-bank"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>

                    <li>
                        <a href="#" onClick={() => setActiveComponent('profile')}>
                            <i className="bi bi-person-lines-fill"></i>
                            <span>Profile</span>
                        </a>
                    </li>

                    <li>
                        <a href="#" onClick={() => setActiveComponent('statistic')}>
                            <i className="bi bi-card-list"></i>
                            <span>Statistic</span>
                        </a>
                    </li>

                    <li className="logout">
                        <a href="#">
                            <i className="bi bi-door-closed"></i>
                            <span><Link to="/">Logout</Link></span>
                        </a>
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


