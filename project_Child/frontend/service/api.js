import axios from 'axios';

const API_URL = 'http://localhost:80800/api';

export const registerUser = (userData) => {
  return axios.post(`${API_URL}/users/register`, userData);
};

export const getGrowthData = (childId) => {
  return axios.get(`${API_URL}/growth-data/${childId}`);
};
