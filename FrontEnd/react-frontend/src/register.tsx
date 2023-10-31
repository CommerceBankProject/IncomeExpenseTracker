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

function validation(email: string, firstName: string, lastName: string, password: string, rePassword: string)
{
  let count: number = 0; 
  if (checkEmail(email) && email !=="")
  {
    count++;
  }

  else
  {
    if (email === "")
    {
      alert("Please enter you email");
    }

    else
    {
      alert("Please enter a valid email");
    }
    //error
  }

  if (password === rePassword)
  {
    count++;
  }

  else
  {
    if (password === "")
    {
      alert("Plse enter your password");
    }

    else
    {
      alert("Both password have to be the same")
    }
  }

  if (firstName !=="")
  {
    count++;
  }
  else{
    alert("Plse enter your first name");
  }

  if (lastName !=="")
  {
    count++;

  }else{
    alert("Plse enter your last name");
  }

  return count;
}

function registerPage() {
  
    const [firstname, setFirstName] = useState("");
    const [lastname, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [rePassword, setRePassword] = useState("");
    async function save(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        event.preventDefault();

        let checkInput: number = validation(email, firstname,lastname,password,rePassword);
        
        if (checkInput < 4)
        {
          return;
        }

        try {
          const response = await axios.post("http://localhost:8080/auth/user_register", {
          firstName: firstname,
          lastName: lastname,
          email: email,
          password: password,
          rePassword: rePassword,
          });

          if (response) {
            toast.success("User created successfully"); // Display a confirmation message
            alert("User data received successfully");
        } else {
            alert("User creation failed"); // Handle other response scenarios
        }
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

            <form>
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
            </form>
        </div>

        <div className="container signin">
        <p>Already have an account? <Link to="/">Sign in</Link>.</p>
        </div>
        </div>
    );
  }
  
  export default registerPage;