import React, { useEffect, useState } from 'react';
import BarGraph from "./BarGraph"
import "./statisticPage.css"


function UserStatistic () {

  const dataArray1 = [
    {
      value: 200,
      legend: 'Deposit',
    },
    {
      value: 300,
      legend: 'Expense',
    }
  ];

  
    return (
      <div>
        <div className="card--container">
          <div className="container">
            <h3>Budget Monthly Report</h3>
            <BarGraph dataArray={dataArray1}/>
          </div>

          <div className="action--card">
            <h5>Transaction List: </h5>
            <option>


            </option>
          </div>


        <div className="action--card">
        <h5>Last Deposit: </h5>
        <h5>Last Expense: </h5>
        </div>
        </div>



        
      </div>
    );
  };



export default UserStatistic;

