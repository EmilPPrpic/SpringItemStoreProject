import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import AdminItemList from './components/AdminItemList';
import ItemForm from './components/ItemForm';
import UserItemList from "./components/UserItemList";

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/admin/items/all" element={<AdminItemList />} />
            <Route path="/user/items/all" element={<UserItemList />} />
          <Route path="/admin/items/create" element={<ItemForm />} />
          <Route path="/admin/items/:name" element={<ItemForm />} />
          <Route path="*" element={<Navigate to="/login" />} />
        </Routes>
      </Router>
  );
}

export default App;
