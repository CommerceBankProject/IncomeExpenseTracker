import React from "react";
import { useEffect, useState } from 'react';
import "./UserProfile.css";
import axios from "axios";
import { useParams } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';


function checkEmail(email: string): boolean{
    const emailFormat = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i ;
    return emailFormat.test(email);
}

function inputVerification(
  
    email: string,
    firstname: string,
    lastname: string,
    password: string,
    rePassword: string
  ): number {
    if (!email || !firstname || !lastname || !password || !rePassword) {
      return 1;
    }
  
    if (!checkEmail(email)) {
      return 2;
    } 
  
    if (password != rePassword) {
      return 3;
    }
  
    return 0;
  }



function UserProfile() 
{
    const [showSaveButton, setShowSaveButton] = useState(false);
    const [inputDisabled, setInputDisabled] = useState(true);
    const [userFirstName, setUserFirstName] = useState('');
    const [userLastName, setUserLastName] = useState('');
    const [userEmail, setUserEmail] = useState('');
    const [userCreateDate, setUserCreateDate] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [userRePassword, setUserRepassword] = useState('');
    const { userId } = useParams<{ userId: string }>();

    useEffect(() => {
        // Use the provided API endpoint with the userId
        axios.get(`http://localhost:8081/user_accounts/${userId}`)//Chang this API
        .then((response) => {
        // Extract firstName and lastName from the response
            const { firstName, lastName, email, password, create_at } = response.data;
            // Set the userName state as the combination of firstName and lastName
            setUserFirstName(`${firstName}`);
            setUserLastName(`${lastName}`);
            setUserEmail(`${email}`);
            setUserPassword(`${password}`);
            setUserCreateDate(`${create_at}`);
            })
            .catch((error) => {
        console.error('API request error', error);
        });
    }, [userId]);

    const handleEditButtonClick = () => {
        setShowSaveButton(true);
        setInputDisabled(false);
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUserFirstName(event.target.value);
        setUserLastName(event.target.value);
        setUserEmail(event.target.value);
        setUserPassword(event.target.value);
        setUserRepassword(event.target.value);
        

    };

    const handleSaveButtonClick = () => {

        const verificationResult = inputVerification(userEmail, userFirstName, userLastName, userPassword, userRePassword);

        if (verificationResult === 1) {
            toast.error("Please fill in all fields", {
              position: "top-right",
            });
          }
  
          else if (verificationResult === 2) {
            toast.error("Invalid email format", {
              position: "top-right",
            });
          }
          
          else if (verificationResult === 3) {
            toast.error("Passwords do not match", {
              position: "top-right",
            });
          }
        
          else {

            setShowSaveButton(false);
            setInputDisabled(true);
  
            try {
              axios.post("http://localhost:8081/auth/user_register", {
              "firstName": userFirstName,
              "lastName": userLastName,
              "email": userEmail,
              "password": userPassword,
              }, {
            headers: {
            'Content-Type': 'application/json'
            }});
              alert("Employee Registration Successful");
            } catch (err) {
              alert(err);
            }
          }
        }


    return (
        <div>
            <ToastContainer />
            <div className="card--container">
                <div className="text--container">
                    <div className="textbox--container">
                        <h5 className="input-label">First Name: </h5>
                        <input type="text" name="FirstName" id="FirstName" value={userFirstName} 
                        
                        onChange={handleInputChange}
                        
                        disabled={inputDisabled}/>
                    </div>
                </div>


                <div className="text--container">
                    <div className="textbox--container">
                        <h5 className="input-label">Last Name: </h5>
                        <input type="text" name="LastName" id="LastName" value={userLastName} 
                        
                        onChange={handleInputChange}
                        
                        disabled={inputDisabled}/>
                    </div>
                </div>
            </div>

            <div className="card--container">
                <div className="text--container">
                    <div className="textbox--container">
                        <h5 className="input-label">Email: </h5>
                        <input type="text" name="Email" id="Email" value= {userEmail} 
                        
                        onChange={handleInputChange}

                        disabled={inputDisabled}/>
                    </div>
                </div>


                <div className="text--container">
                    <div className="textbox--container">
                        <h5 className="input-label">Create Date: </h5>
                        <input type="text" name="CreateDate" id="CreateDate" value={userCreateDate} disabled/>
                    </div>
                </div>
            </div>

            <div className="card--container">
                <div className="text--container">
                    <div className="textbox--container">
                        <h5 className="input-label">Password: </h5>
                        <input type="password" name="Password" id="Password" value={userPassword} 
                        
                        onChange={handleInputChange}
                        
                        disabled={inputDisabled}/>
                    </div>
                </div>


                <div className="text--container">
                    <div className="textbox--container">
                        <h5 className="input-label">Repeat Password: </h5>
                        <input type="password" name="RePassword" id="Repassword"  
                        
                        onChange={handleInputChange}
                        
                        disabled={inputDisabled}/>
                    </div>
                </div>
            </div>


            <div className="card--container">
                <div className="text--container">
                    <div className="textbox--container">
                        <h5 className="input-label">Last update: </h5>
                        <h5></h5>
                    </div>
                </div>


                <div className="text--container">
                    <div className="textbox--container">
                        <button onClick={handleEditButtonClick}>Edit profile</button>
                        {showSaveButton && (<button onClick={handleSaveButtonClick}>Save profile</button>)}
                    </div>
                </div>
            </div>


        </div>       
    );
}

export default UserProfile;