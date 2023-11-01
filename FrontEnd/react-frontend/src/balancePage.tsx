import React from "react";
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import "./balancePage.css";
import 'bootstrap-icons/font/bootstrap-icons.css';
import axios from 'axios';

const balancePage: React.FC = () => {
    const [accountType, setAccountType] = useState<String>('');
    const [accountNumber, setCardNumber] = useState<string>('');
    const [balance, setAmountValue] = useState<number>(0);

    // Extracting the ID from the URL
    const { id } = useParams<{ id: string }>();

    useEffect(() => {
        // Make a GET request to the backend API to fetch accountType and cardNumber
        axios.get('http://localhost:8081/user_accounts/${id}/bank_accounts') // Replace with the correct API endpoint
          .then((response) => {
            const accounts = response.data;
            const { accountType, accountNumber, balance } = accounts[0];
            setAccountType(accountType);
            setCardNumber(accountNumber);
            setAmountValue(balance);
          })
          .catch((error) => {
            console.error('API request error', error);
          });
      }, [id]);

    return (
    <div className="card--wrapper">
        <div className="payment--card">
            <div className="amount">
                <span className="title" id="accountType">Account type </span>
                <span className="amount-value" id="balance">$500</span>
            </div>
            <i className="bi bi-currency-dollar"></i>
        </div>
        <span className="card-detail" id="accountNumber">**** **** **** 1234</span>
    </div>

    
)};

export default balancePage;