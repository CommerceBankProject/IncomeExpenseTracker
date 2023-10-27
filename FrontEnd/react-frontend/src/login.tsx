import "./login.css"
import bankLogo from "./Image/logo.jpg"
import { useState } from "react";
import { redirect, useNavigate } from 'react-router-dom';
import axios from "axios"; // Making HTTP requests from a web browser 
import { Link } from 'react-router-dom';
import React from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';



function LoginPage() {
   
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errorCount, setErrorCount] = useState(0);
    
   
    const MAX_ERROR_COUNT = 3;
    const navigate = useNavigate();

    async function login(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        event.preventDefault();
    
          if (!email || !password)
        {
          if (errorCount < MAX_ERROR_COUNT) {
            toast.error("Please enter both email and password.",
          {
            position: "top-right",
          });
          setErrorCount(errorCount + 1);
          }
          //Add the "error" class to email input
          return;
        }
        
        try {
          await axios.post("http://localhost:8081/api/user_login", {
            email: email,
            password: password,
            }).then((res) => 
            {
             console.log(res.data);
             
             if (res.data.message == "Email not exits") 
             {
              toast.error("Email address is not exist",
              {
                position: "top-right",
              });
             } 
             else if(res.data.message == "Login Success")
             { 
              toast.info("Login Success",
              {
                position: "top-right",
              });
                
                //navigate('/home');
             } 
              else 
             { 
              toast.error("Incorrect Email and Password not match",
              {
                position: "top-right",
              });
             }
          }, fail => {
            
           console.error(fail); // Error!
  });
        }
 
         catch (err) {
          alert(err);
        }
      
      }

      
      return (
        <div  className="bg">
            <div className="left-login">
                <header>
                  <ToastContainer />
                    <div className="com-brand">
                        <header><img src = {bankLogo} alt="Bank logo" className="logo"></img></header>
                        <section>
                            <header>
                                <h2>Sign In</h2>
                            </header>
    
                            <section>
                                <label>Email ID: </ label><br />
                                <input type="text" className="input-style" id="email" name="email" 
                                value={email}
                                onChange={(event) => {
                                setEmail(event.target.value);
                                }
                                
                                }
                               /><br /><br />

                                <label>Password: </label><br />
                                <input type="password" className="input-style" id="password" name="password" 
                                
                                value={password}
                                onChange={(event) => {
                                setPassword(event.target.value);
                                }}
                                
                                /><br /><br />
                                <a href="url">forget password</a> <br /><br />
                                <input type="checkbox" id="rememberEmail" name="rememberEmail" value="reEmail" />
                                <label> Remember my Email ID</label><br /><br />
                                <button type="submit" onClick={(event) => login(event)}>Sign In</button><br /><br />
                                <Link to="/user_register">Create an account</Link>
                            </section>
                        </section>
                        <footer>
    
                        </footer>
                    </div>
                </header>
            </div>
        </div>
      );
  }


export default LoginPage; 