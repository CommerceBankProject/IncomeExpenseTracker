
import axios from "axios";
import React, { useState } from "react";
import "./linkAccount.css";

interface DepositPopupProps {
  onClose: () => void;
 
}

const DepositPopup: React.FC<DepositPopupProps> = ({ onClose }) => {
  const [balance, setBalance] = useState<number>(0);
  const [bankName, setBankName] = useState("");
  const [accountType, setAccontType] = useState("");
  const [accountAlias, setAccountAlias] = useState("");

  const handleDeposit = async () => {
    try {
      // Make a POST request to the backend API with the deposit data
      await axios.post("http://localhost:8081/user_bank_account_links/create_account_and_link", {
            "userId": userId,
            "bankName": bankName,
            "accountType": accountType,
            "initialBalance": amount,
            "accountAlias": accountAlias
          }, {
          headers: {
          'Content-Type': 'application/json'
          }
          });

      // Close the popup after successful deposit
      onClose();
      alert("Deposit Successful");
    } catch (err) {
      alert(err);
    }
  };

  return (
    <div className="account-popup">
      <div className="accountPopup-content">
        <h3> Bank Name: </h3>
        <input type="text" 
        value={bankName}
        onChange={(event) => {
            setBankName(event.target.value);
        }}
        ></input>

        <h3> Account Type : </h3>
        <input type="text" 
        value={accountType}
        onChange={(event) => {
            setAccontType(event.target.value);
        }}
        
        ></input>

        <h3> Initial Balance: </h3>
        <input type="number" className="input-num" value={balance} onChange={(e) => setBalance(parseFloat(e.target.value))} />

        <h3> Account Alias : </h3>
        <input type="text" 
        value={accountAlias}
        onChange={(event) => {
            setAccountAlias(event.target.value);
        }}
        ></input>


      
      <button onClick={handleDeposit}>Link Account</button>
      <button onClick={onClose}>Cancel</button>
    </div>
  </div>
  );
};

export default DepositPopup;
