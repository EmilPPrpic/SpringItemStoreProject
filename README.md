# Store Item Manager

A full-stack web application for managing store inventory.  
It allows admins to add, update, and delete items, while users can browse available products.

The **frontend** (React-based) is minimal and primarily serves to demonstrate basic interaction with the backend (login, item management, display). It can easily be extended or replaced depending on the intended use case.

---

## 🌐 Overview

This system is designed for:

- **Item entry and management** (admin interface)
- **Item display and filtering** (user interface)

---

## 🛠️ Features

| Feature                        | Admin | User |
|-------------------------------|:-----:|:----:|
| View all items                | ✅    | ✅   |
| Filter by availability        | ✅    | ✅   |
| Add new items                 | ✅    | ❌   |
| Edit existing items           | ✅    | ❌   |
| Delete items                  | ✅    | ❌   |
| Role-based login              | ✅    | ✅   |

---

## 👥 User Roles

### Admin
- Full CRUD access to item records.
- Can access the `/admin/items/*` endpoints.

### User
- Can only view and filter items.
- Has access to the `/user/items/*` endpoints.

---

## 🔑 Default Accounts

To get started with the application, you can use the following default accounts:

- **Admin Account**
    - **Username**: `admin`
    - **Password**: `admin123`

- **User Account**
    - **Username**: `user`
    - **Password**: `password123`


## 🗃️ Item Table Schema (SQL)
| Column         | Type             | Constraints                         | Description                                 |
|----------------|------------------|-------------------------------------|---------------------------------------------|
| `id`           | BIGINT           | PRIMARY KEY, AUTO_INCREMENT         | Unique identifier for the item              |
| `name`         | VARCHAR(255)     | NOT NULL, UNIQUE                    | Name of the item                            |
| `description`  | VARCHAR(1000)    |                                     | Description of the item                     |
| `price`        | DOUBLE PRECISION | NOT NULL                            | Price of the item                           |
| `availability` | VARCHAR(255)     | NOT NULL                            | Enum: `AVAILABLE`, `UNAVAILABLE`, `ON_DEMAND` |
| `image_url`    | VARCHAR(255)     |                                     | Optional image URL for display              |


---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Node.js + npm
- Docker (for PostgreSQL)

---

## 🚀 Future Improvements
This project currently serves as a foundation for managing items in a store, supporting administrative input and user-facing views. Here are some areas where the system could be expanded or improved:

### 🔐 Authentication & Security
- **Switch to JWT-based Authentication**: Replace session-based login with JWT (JSON Web Token) to support stateless authentication and easier integration with front-end applications or mobile clients.
- **Role-based Access Enhancements**: Fine-grain the permissions for both roles and potentially add more (e.g., Editor, Viewer).

### 🔍 Search & Filtering
- **Advanced Search Functionality**: Allow users to search items by name, description, or price range.
- **Filtering by Availability or Category**: Improve UX by enabling users to filter items by `Availability` (AVAILABLE, UNAVAILABLE, ON_DEMAND) or by item category (if implemented).

### 🧠 Extensibility and Use Cases
The current system is a base framework that can evolve into several directions:
- **🛒 Online Store**: Add cart functionality, payment processing, order tracking.
- **🏬 In-House Inventory Management**: Add stock quantity, supplier management, restocking alerts.
- **📚 Item Catalogue System**: Public-facing catalogue for display only, no purchase flow.

Development was intentionally paused at this point to keep the project open-ended and adaptable for your specific use case.

