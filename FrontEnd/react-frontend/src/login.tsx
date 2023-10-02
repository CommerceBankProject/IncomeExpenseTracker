import "./login.css"
import bankLogo from "./Image/logo.jpg"
import React, { Component } from 'react';
 
class LoginPage extends Component {
    render() {
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
                                <input type="checkbox" id="vehicle1" name="vehicle1" value="Bike" />
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
  }

export default LoginPage; 