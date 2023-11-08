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
    <div>  
    <div className="card--container">
      <h3 className="main--title">Today's data</h3>
      <div className="card--wrapper">
        <div className="payment--card">
          <div className="card--header">
            <div className="amount">
                <span className="title" id="accountType">Account type </span>
                <span className="amount-value" id="balance">$500</span>
            </div>
            <i className="bi bi-currency-dollar icon"></i>
            
        </div>
        <span className="card-detail" id="accountNumber">**** **** **** 1234</span>
        </div>

        <div className="action--card">
        <button type="submit" className="buttonDeposit">Deposit</button>
        <button type="submit" className="buttonExpense">Expense</button>
        </div>
     </div>
    </div>
    <div className="tabular--wrapper">
      <h3 className="main--title">Finance Data</h3>
      <div className="table-container">
        <table>
          <thead>
          <tr>
            <th>Transaction Type</th>
            <th>Description</th>
            <th>Amount</th>
            <th>Date</th>
          </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                Expense
              </td>
              <td>
                Food Supplies
              </td>
              <td>
               $300.00
              </td>
              <td>
                11-07-2023
              </td>
            </tr>
          </tbody>
        </table>
      </div>

    </div>
    </div>

  

    
)};

export default balancePage;