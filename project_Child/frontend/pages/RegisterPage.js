import React, { useState } from 'react';
import { registerUser } from '../services/api';

function RegisterPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleRegister = async (e) => {
    e.preventDefault();
    const userData = { email, password };
    try {
      await registerUser(userData);
      alert('Đăng ký thành công!');
    } catch (error) {
      alert('Đã có lỗi xảy ra');
    }
  };

  return (
    <div>
      <h2>Đăng ký</h2>
      <form onSubmit={handleRegister}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Mật khẩu"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Đăng ký</button>
      </form>
    </div>
  );
}

export default RegisterPage;
