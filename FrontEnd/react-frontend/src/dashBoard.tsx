import { useEffect,useState } from "react";
import BalancePage from "./balancePage";
import "./dashBoard.css"
import 'bootstrap-icons/font/bootstrap-icons.css';
import axios from "axios"; 

function DashBoard() {
    const [activeComponent, setActiveComponent] = useState<'balancepage' | 'profile'>('balancepage');

    const renderComponent = () => {
        if (activeComponent === 'balancepage'){
            return <BalancePage />;
        }

        else
        {
            return null;
        }
    }

    const [userName, setUserName] = useState<String>('');
    useEffect(() => {
        // Make a GET request to the backend API to fetch accountType and cardNumber
        axios.get('api Request here') // Replace with the correct API endpoint
          .then((response) => {
            const { userName } = response.data;
            setUserName(userName);
            
          })
          .catch((error) => {
            console.error('API request error', error);
          });
      }, []);



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
                        <a href="#">
                            <i className="bi bi-person-lines-fill"></i>
                            <span>Profile</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <i className="bi bi-card-list"></i>
                            <span>Statistic</span>
                        </a>
                    </li>

                    <li className="logout">
                        <a href="#">
                            <i className="bi bi-door-closed"></i>
                            <span>Logout</span>
                        </a>
                    </li>
                </ul>
            </div>

            <div className="main--content">
                <div className="header--wrapper">
                    <div className="header--title">
                        <span>Welcome</span>
                        <h2 id="userName">UserName</h2>
                    </div>
                    <div className="user--infor">
                        <div className="search--box">
                    
                        <input type="text" placeholder="Search"/>
                    </div>
                    <button type="submit" >Search</button>
                </div>
            </div>

            <div className="card--container">
                <h3 className="main--title">Today's data</h3>
                {renderComponent()}
            </div>
            
               
        </div>
        </div>
    );
}

export default DashBoard;


