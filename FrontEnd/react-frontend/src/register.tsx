import { useState } from "react";
import "./register.css"
import axios from "axios";
import { Link } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function checkEmail(email: string): boolean{
  const emailFormat = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i ;
  return emailFormat.test(email);
}

function registerPage() {
  
    const [firstname, setFirstName] = useState("");
    const [lastname, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [rePassword, setRePassword] = useState("");
    async function save(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        event.preventDefault();
        if (password != rePassword)
        {

           return;
        }

        try {
          await axios.post("http://localhost:8081/auth/user_register", {
          firstname: firstname,
          lastname: lastname,
          email: email,
          password: password,
          rePassword: rePassword,
          });
          alert("Employee Registation Successfully");
        } catch (err) {
          alert(err);
        }
      }
  
    return (
        <div> 
        <div className="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr></hr>

            <label><b>First Name</b></label>
            <input type="text" placeholder="First Name" name="firstname" id="firstname" required 
            
            value = {firstname}
            onChange={(event) => {
                setFirstName(event.target.value);
              }}
            
            />

            <label><b>Last Name</b></label>
            <input type="text" placeholder="Last Name" name="lastname" id="lastname" required 
            
            value = {lastname}
            onChange={(event) => {
                setLastName(event.target.value);
              }}
            
            />

            <label ><b>Email</b></label>
            <input type="text" placeholder="Email" name="email" id="email" required 
            
            value = {email}
            onChange={(event) => {
                setEmail(event.target.value);
              }}
            
            />

            <label><b>Password</b></label>
            <input type="password" placeholder="Password" name="psw" id="psw" required 
            
            value = {password}
            onChange={(event) => {
                setPassword(event.target.value);
              }}
            
            />

            <label><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="rePassword" id="rePassword" required 
            
            value = {rePassword}
            onChange={(event) => {
                setRePassword(event.target.value);
              }}
            
            />
            <hr></hr>
            <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

            <button type="submit" className="registerbtn" onClick={save}>Register</button>
        </div>

        <div className="container signin">
        <p>Already have an account? <Link to="/">Sign in</Link>.</p>
        </div>
        </div>
    );
  }
  
  export default registerPage;