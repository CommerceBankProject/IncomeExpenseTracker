import "./login.css"
import bankLogo from "./Image/logo.jpg"
import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function LoginPage() {
   
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    async function login(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        event.preventDefault();
        try {
          await axios.post("http://localhost:8081/api/user_login", {
            email: email,
            password: password,
            }).then((res) => 
            {
             console.log(res.data);
             
             if (res.data.message == "Email not exits") 
             {
               alert("Email not exits");
             } 
             else if(res.data.message == "Login Success")
             { 
                
                //navigate('/home');
             } 
              else 
             { 
                alert("Incorrect Email and Password not match");
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
                    <div className="com-brand">
                        <header><img src = {bankLogo} alt="Bank logo" className="logo"></img></header>
                        <section>
                            <header>
                                <h2>Sign In</h2>
                            </header>
    
                            <section>
                                <label>Email ID: </ label><br />
                                <input type="email" id="email" name="email" 
                                value={email}
                                onChange={(event) => {
                                setEmail(event.target.value);
                                }}/><br /><br />

                                <label>Password: </label><br />
                                <input type="password" id="password" name="password" 
                                
                                value={password}
                                onChange={(event) => {
                                setPassword(event.target.value);
                                }}
                                
                                /><br /><br />
                                <a href="url">forget password</a> <br /><br />
                                <input type="checkbox" id="rememberEmail" name="rememberEmail" value="reEmail" />
                                <label> Remember my Email ID</label><br /><br />
                                <button type="submit" onClick={(event) => login(event)}>Sign In</button>
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