import React from 'react';
import './BarItem.css';

export type Props = {
    legend: string;
    barPercentage: string;
  };

export default function BarItems(props: Props) {
  return <div className="bar-item" style={{ height: props.barPercentage }}>
    <div className="bar-item-legend">{props.legend}</div>
  </div>;
}