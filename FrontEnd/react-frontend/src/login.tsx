import "./login.css"
import bankLogo from "./Image/logo.jpg"
import React, { useState, useEffect } from 'react';

function LoginPage()
{
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleLogin = async () => {
        try {
            const response = await fetch('http://localhost:5173/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type' : 'application/json',
                },
                body: JSON.stringify({email, password}),
            });

            if (response.ok)
            {
                 // Login successful
            }
            else
            {
                setError('Invalid pleas try again');
            }
        }catch (error)
    {
        setError('An error occurred. Please try again later.');
    }
};

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
                            <input type="text" id="emailId" name="emailId" /><br /><br />
                            <label>Password: </label><br />
                            <input type="password" id="uPassword" name="uPassword" /><br /><br />
                            <a href="url">forget password</a> <br /><br />
                            <input type="checkbox" id="rememberEmail" name="rememberEmail" value="reEmail" />
                            <label> Remember my Email ID</label><br /><br />
                            <button name="subject" type="submit" value="HTML">Sign In</button>
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