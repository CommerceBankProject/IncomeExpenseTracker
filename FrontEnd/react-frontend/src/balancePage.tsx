import React from "react";
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import "./balancePage.css";
import 'bootstrap-icons/font/bootstrap-icons.css';
import DepositPopup from './DepositPopup';
import axios from 'axios';
import ExpensePopup from "./ExpensePopup";
import LinkAccount from "./linkAccount";

// BalancePage component
interface BalancePageProps {
    userId: string;
}

const balancePage: React.FC<BalancePageProps> = ({ userId }) => {
    const [selectedAccount, setSelectedAccount] = useState({
        accountType: '',
        accountNumber: '',
        balance: 0,
    });
    const [accounts, setAccounts] = useState<any[]>([]);
    const [isDepositPopupOpen, setDepositPopupOpen] = useState(false);
    const [isExpensePopupOpen, setExpensePopupOpen] = useState(false);
    const [isLinkAccountPopupOpen, setLinkAccountPopupOpen] = useState(false);
    const [transactions, setTransactions] = useState([]);


    useEffect(() => {
        axios.get(`http://localhost:8081/user_accounts/${userId}/bank_accounts`)
            .then((response) => {
                setAccounts(response.data);
                if (response.data.length > 0) {
                    setSelectedAccount(response.data[0]);
                }
            })
            .catch((error) => {
                console.error('API request error', error);
            });
    }, [userId]);

    useEffect(() => {
            if (selectedAccount.accountNumber) {
                axios.get(`http://localhost:8081/api/transactions/account/${selectedAccount.accountNumber}`)
                    .then((response) => {
                        setTransactions(response.data);
                    })
                    .catch((error) => {
                        console.error('Error fetching transactions:', error);
                    });
            }
        }, [selectedAccount.accountNumber]);

    const handleAccountSelection = (event) => {
        const accountNumber = event.target.value;
        const account = accounts.find(acc => acc.accountNumber === accountNumber);
        if (account) {
            setSelectedAccount(account);
        }
    };

    return (
    <div>
    <div className="card--container">
      <h3 className="main--title">Today's data</h3>
      <div className="card--wrapper">
        <div className="payment--card">
          <div className="card--header">
            <div className="amount">
                <span className="title" id="accountType">{selectedAccount.accountType}</span>
                <span className="amount-value" id="balance">${selectedAccount.balance}</span>
            </div>
            <i className="bi bi-currency-dollar icon"></i>

        </div>
        <span className="card-detail" id="accountNumber">**** **** **** {selectedAccount.accountNumber.slice(-4)}</span>
        </div>

        <div className="action--card">
        <button  className="buttonDeposit" onClick={() => setDepositPopupOpen(true)} >Deposit</button>
        {isDepositPopupOpen && (<DepositPopup onClose={() => setDepositPopupOpen(false)} />)}
        <button type="submit" className="buttonExpense" onClick={() => setExpensePopupOpen(true)}>Expense</button>
        {isExpensePopupOpen && (<ExpensePopup onClose={() => setExpensePopupOpen(false)} />)}
        </div>


        <div className="action--displaybox">
          <div className="action--textfile">

        <div className="account-selection">
            <select className="account-select" onChange={handleAccountSelection} value={selectedAccount.accountNumber}>
                {accounts.map((account, index) => (
                    <option key={index} value={account.accountNumber}>
                        {account.accountNumber}
                    </option>
                ))}
            </select>
        </div>

          </div>
        </div>


        <div className="action--card">
        <button type="submit" className="buttonExpense" onClick={() => setLinkAccountPopupOpen(true)}>Link Account</button>
            {isLinkAccountPopupOpen && (<LinkAccount onClose={() => setLinkAccountPopupOpen(false)} />)}
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
                {transactions.map((transaction, index) => (
                    <tr key={index}>
                        <td>{transaction.type}</td>
                        <td>{transaction.description}</td>
                        <td>${transaction.amount.toFixed(2)}</td>
                        <td>{new Date(transaction.date).toLocaleDateString()}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    </div>

    </div>
    </div>




)};

export default balancePage;