import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setError('');
        try {
            // Note: We don't have to write http://localhost:8080/api because our interceptor does it!
            const response = await api.post('/auth/login', { email, password });
            const token = response.data.token;

            // We decode the JWT to get the user's role (you can also just return the role from the backend)
            // For now, we will store a hardcoded mock user until we decode the JWT properly
            const user = { email: email, role: 'STUDENT' }; // We will fix role routing later!

            login(user, token);
            navigate('/dashboard'); // Route them to a dashboard
        } catch (err) {
            setError(err.response?.data?.error || 'Login failed. Please check your credentials.');
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="col-md-5 col-lg-4">
                <div className="glass-card">
                    <h2 className="text-center mb-4 font-weight-bold">PMS Portal</h2>

                    {error && <div className="alert alert-danger p-2 text-center">{error}</div>}

                    <form onSubmit={handleLogin}>
                        <div className="mb-3">
                            <label className="form-label text-light">Email Address</label>
                            <input
                                type="email"
                                className="form-control premium-input"
                                placeholder="name@college.edu"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label className="form-label text-light">Password</label>
                            <input
                                type="password"
                                className="form-control premium-input"
                                placeholder="••••••••"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                        <button type="submit" className="btn btn-premium w-100 py-2">
                            Sign In
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
