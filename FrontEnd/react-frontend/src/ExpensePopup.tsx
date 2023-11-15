
import axios from "axios";
import React, { useState } from "react";
import "./DepositPopup.css";

interface DepositPopupProps {
  onClose: () => void;
 
}

const DepositPopup: React.FC<DepositPopupProps> = ({ onClose }) => {
  const [amount, setAmount] = useState<number>(0);

  const handleDeposit = async () => {
    try {
      // Make a POST request to the backend API with the deposit data
      await axios.post("http://localhost:8081/deposit", {
        amount: amount,
        // other data as needed
      });

      // Close the popup after successful deposit
      onClose();
      alert("Deposit Successful");
    } catch (err) {
      alert(err);
    }
  };

  return (
    <div className="deposit-popup">
      <div className="popup-content">
        <h3> Enter Amount: </h3>
        <input type="number" className="input-num" value={amount} onChange={(e) => setAmount(parseFloat(e.target.value))} />

        <h3> Description: </h3>
        <input type="text"></input>
      
      <button onClick={handleDeposit}>Expense</button>
      <button onClick={onClose}>Cancel</button>
    </div>
  </div>
  );
};

export default DepositPopup;
