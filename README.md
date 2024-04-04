## Classes:
- **User**
  - Admin
  - Customer
- **Product**
  - Electronics
  - Furniture
  - Clothing
- **Order**
- **Rating**
- **ShoppingCart**
- **CreditCard** (implements Payment)
- **Paypal** (implements Payment)
- **Application**

## Interface:
- Payment

---

## Overview:

### Global Variables:
- `inventory`
- `users`
- `codes`

### Initialization:
- `codes` ArrayList initialized with discount codes
- `users` initialized with one admin (username: A, password: 1)
- `inventories` initialized with some items

### CurrentUser Variable:
- Checks the current status of the account (logged in, logged out)

### Signup and Login:
- Signup option restricted to Customers for security reasons
- Redirects to adminMenu or customerMenu based on `currentUser`

### Shopping Stage:
- Specialized in selling Furniture, Electronics, and Clothing
- User is casted either to Customer or Admin for relevant methods
- Avoids duplicate product entries using `equalsIgnoreCase`

#### Additional Features:
- Dynamic search based on keyword
- 10% discount with `getDiscount` method using pre-declared codes
- Payment process based on Strategy pattern using `paymentStrategy`
- Empties cart on logout for a fresh start
- `remainingQuantity` variable prevents modifying quantities until payment process is completed
- `initializeQuantity` method restores initial values if no purchases were made before logout

### Additional Notes:
- Logout empties the cart for safe functioning of the app
- `remainingQuantity` used to prevent modifying quantities until payment process is complete
