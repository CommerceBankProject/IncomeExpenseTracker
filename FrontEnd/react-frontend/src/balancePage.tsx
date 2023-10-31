import React from "react";
import { useEffect, useState } from 'react';
import "./balancePage.css";
import 'bootstrap-icons/font/bootstrap-icons.css';
import axios from 'axios';

const balancePage: React.FC = () => {
    const [accountType, setAccountType] = useState<String>('');
    const [cardNumber, setCardNumber] = useState<string>('');
    const [amountValue, setAmountValue] = useState<string>('');

    useEffect(() => {
        // Make a GET request to the backend API to fetch accountType and cardNumber
        axios.get('api Request here') // Replace with the correct API endpoint
          .then((response) => {
            const { accountType, cardNumber, amountValue } = response.data;
            setAccountType(accountType);
            setCardNumber(cardNumber);
            setAmountValue(amountValue);
          })
          .catch((error) => {
            console.error('API request error', error);
          });
      }, []);

    return (
    <div className="card--wrapper">
        <div className="payment--card">
            <div className="amount">
                <span className="title" id="accountType">Account type </span>
                <span className="amount-value" id="amountValue">$500</span>
            </div>
            <i className="bi bi-currency-dollar"></i>
        </div>
        <span className="card-detail" id="cardNumber">**** **** **** 1234</span>
    </div>

    
)};

export default balancePage;