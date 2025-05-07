import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';

function AdminItemList() {
    const [items, setItems] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch('http://localhost:8080/admin/items/all', {
            credentials: 'include',
        })
            .then(res => res.json())
            .then(data => setItems(data))
            .catch(err => console.error('Error fetching items:', err));
    }, [navigate]);

    const handleLogout = async () => {
        try {
            const response = await fetch('http://localhost:8080/logout', {
                method: 'POST',
                credentials: 'include',
            });

            if (response.ok) {
                navigate('/login');
            } else {
                console.error('Logout failed');
            }
        } catch (error) {
            console.error('Logout failed:', error);
        }
    };

    const getStatusColor = (availability) => {
        switch (availability) {
            case 'AVAILABLE':
                return 'green';
            case 'UNAVAILABLE':
                return 'red';
            case 'ON_DEMAND':
                return 'orange';
            default:
                return 'gray';
        }
    };

    return (
        <div style={{padding: '20px', fontFamily: 'Arial'}}>
            <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center'}}>
                <h2>Items</h2>
                <button
                    onClick={handleLogout}
                    style={{
                        backgroundColor: '#d9534f',
                        color: 'white',
                        border: 'none',
                        padding: '8px 16px',
                        borderRadius: '4px',
                        cursor: 'pointer',
                    }}
                >
                    Logout
                </button>
            </div>
            <button
                onClick={() => navigate('/admin/items/create')}
                style={{
                    marginBottom: '20px',
                    padding: '10px 20px',
                    fontSize: '16px',
                    cursor: 'pointer',
                }}
            >
                Add New Item
            </button>
            <div style={{display: 'flex', flexWrap: 'wrap', gap: '20px'}}>
                {items.map(item => (
                    <div
                        key={item.name}
                        onClick={() => navigate(`/admin/items/${item.name}`)}
                        style={{
                            border: '1px solid #ccc',
                            borderRadius: '8px',
                            padding: '15px',
                            width: '250px',
                            cursor: 'pointer',
                            boxShadow: '2px 2px 8px rgba(0,0,0,0.1)',
                            backgroundColor: '#f9f9f9',
                            transition: 'transform 0.2s',
                        }}
                        onMouseEnter={e => e.currentTarget.style.transform = 'scale(1.02)'}
                        onMouseLeave={e => e.currentTarget.style.transform = 'scale(1)'}
                    >
                        <h3>{item.name}</h3>
                        <p>{item.description}</p>
                        <p><strong>Price:</strong> ${item.price.toFixed(2)}</p>
                        <div style={{display: 'flex', alignItems: 'center', gap: '8px'}}>
                            <span
                                style={{
                                    width: '12px',
                                    height: '12px',
                                    borderRadius: '50%',
                                    backgroundColor: getStatusColor(item.availability),
                                    display: 'inline-block',
                                }}
                            ></span>
                            <span style={{fontSize: '14px'}}>{item.availability}</span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default AdminItemList;
