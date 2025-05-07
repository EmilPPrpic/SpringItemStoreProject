import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function ItemForm() {
    const { name } = useParams();
    const [item, setItem] = useState({
        name: '',
        description: '',
        price: 0,
        availability: 'AVAILABLE',
    });
    const navigate = useNavigate();

    useEffect(() => {
        if (name) {
            fetch(`http://localhost:8080/admin/items/get/${name}`, {
                credentials: 'include',
            })
                .then(res => {
                    if (!res.ok) throw new Error('Item not found');
                    return res.json();
                })
                .then(data => setItem(data))
                .catch(console.error);
        }
    }, [name]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const method = name ? 'PUT' : 'POST';
        const url = name
            ? `http://localhost:8080/admin/items/update/${name}`
            : 'http://localhost:8080/admin/items/create';

        await fetch(url, {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(item),
            credentials: 'include',
        });

        navigate('/admin/items/all');
    };

    const handleDelete = async () => {
        await fetch(`http://localhost:8080/admin/items/delete/${name}`, {
            method: 'DELETE',
            credentials: 'include',
        });
        navigate('/admin/items/all');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>{name ? 'Edit' : 'Create'} Item</h2>

            <input
                type="text"
                value={item.name}
                onChange={(e) => setItem({ ...item, name: e.target.value })}
                placeholder="Name"
                required
                disabled={!!name}
            />

            <input
                type="text"
                value={item.description}
                onChange={(e) => setItem({ ...item, description: e.target.value })}
                placeholder="Description"
                required
            />

            <input
                type="number"
                value={item.price}
                onChange={(e) => setItem({ ...item, price: parseFloat(e.target.value) })}
                placeholder="Price"
                required
                step="0.01"
            />

            <select
                value={item.availability}
                onChange={(e) => setItem({ ...item, availability: e.target.value })}
            >
                <option value="AVAILABLE">Available</option>
                <option value="UNAVAILABLE">Unavailable</option>
                <option value="ON_DEMAND">On Demand</option>
            </select>

            <button type="submit">{name ? 'Update' : 'Create'}</button>
            {name && <button type="button" onClick={handleDelete}>Delete</button>}
        </form>
    );
}

export default ItemForm;
