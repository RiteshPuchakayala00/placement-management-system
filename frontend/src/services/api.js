import axios from 'axios';

// Create a custom instance of axios
const api = axios.create({
    baseURL: 'http://localhost:8080/api', // This points to your Spring Boot server!
    headers: {
        'Content-Type': 'application/json'
    }
});

// The Interceptor: This runs BEFORE every request is sent out
api.interceptors.request.use(
    (config) => {
        // 1. Check local storage for the token
        const token = localStorage.getItem('token');

        // 2. If it exists, attach it to the Authorization header
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default api;
