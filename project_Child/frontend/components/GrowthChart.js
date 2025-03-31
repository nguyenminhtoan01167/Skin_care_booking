import React from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

function GrowthChart({ growthData }) {
  return (
    <ResponsiveContainer width="100%" height={400}>
      <LineChart data={growthData}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="weight" stroke="#8884d8" />
        <Line type="monotone" dataKey="height" stroke="#82ca9d" />
      </LineChart>
    </ResponsiveContainer>
  );
}

export default GrowthChart;
