import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';

function App() {
  return (
      <Router>
        <Routes>
          {/* The default route is our beautiful login page! */}
          <Route path="/" element={<LoginPage />} />

          {/* We will build this dashboard next! */}
          <Route path="/dashboard" element={<h1 className="text-center mt-5 text-white">Welcome to the Dashboard!</h1>} />
        </Routes>
      </Router>
  );
}

export default App;
