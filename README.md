# Cash Register App User Manual

## Table of Contents
1. [Introduction](#1-introduction)
2. [Installation](#2-installation)
3. [Getting Started](#3-getting-started)
4. [Ordering Items](#4-ordering-items)
5. [Payment Processing](#5-payment-processing)
6. [Additional Features](#6-additional-features)
7. [Troubleshooting](#7-troubleshooting)
8. [Resetting the App](#8-resetting-the-app)
9. [Technical Support](#9-technical-support)
10. [System Architecture](#10-system-architecture)

---

## 1. Introduction

The Cash Register App is an integrated solution designed for school cafeteria operations. It combines:
- An Android-based front-end for cashiers
- A Python-based backend API for data processing
- A MariaDB database for data storage
- Automated scheduling via cron jobs

---

## 2. Installation

### Android App Installation
1. Build the APK from Android Studio
2. Wait for the build completion notification (approximately 30 seconds)
3. Click "locate" to find the APK file
4. Transfer the APK to the target tablet
5. Install the APK on the tablet

### Backend Installation
1. Run the install.sh script to set up:
   - Required system packages
   - Python virtual environment
   - Database server
   - Automated scheduling
2. Verify the installation by checking:
   - MariaDB service status
   - Cron job configuration
   - API accessibility

---

## 3. Getting Started

Upon launching the Cash Register App, you will be presented with the main screen, displaying various buttons and displays. The key components include:

- **Item Count Displays:** Show the quantity of each item category.
- **Total Price Display:** Indicates the current total cost of the order.
- **Amount Given Input:** Allows you to input the amount given by the customer.

---

## 4. Ordering Items

### Main Tray Items
- Use the "Add" button to increase the quantity.
- Use the "Del" button to decrease the quantity.
- Each addition increases the total price by $8.
  
<img src="https://github.com/SICTCCS/LunchBoxCashRegister/blob/main/Images/Meal%20Item.png" height="200px" width="800px"></kbd><br>

### On-Tray Items
- Use the respective "Add" and "Del" buttons to manage quantities.
- Each addition increases the total price by $3.
  
<img src="https://github.com/SICTCCS/LunchBoxCashRegister/blob/main/Images/Entree%20Item.png" height="200px" width="800px"></kbd><br>

### Roll Items
- Adjust quantities using the "Add" and "Del" buttons.
- Each addition increases the total price by $0.50.
- 
<img src="https://github.com/SICTCCS/LunchBoxCashRegister/blob/main/Images/Roll%20Item.png" height="200px" width="800px"></kbd><br>

### Dessert Items
- Modify quantities with the "Add" and "Del" buttons.
- Each addition increases the total price by $2.
- 
<img src="https://github.com/SICTCCS/LunchBoxCashRegister/blob/main/Images/Dessert%20Item.png" height="200px" width="800px"></kbd><br>

### Cookie Items
- Manage quantities using the "Add" and "Del" buttons.
- Each addition increases the total price by $1.
- 
<img src="https://github.com/SICTCCS/LunchBoxCashRegister/blob/main/Images/Cookie%20Item.png" height="200px" width="800px"></kbd><br>

---

## 5. Payment Processing

To process payments, follow these steps:

1. Enter the amount given by the customer in the "Amount Given" field.
2. Press the "Calculate" button.
3. The change due to the customer will be displayed.
 -  
<img src="https://github.com/SICTCCS/LunchBoxCashRegister/blob/main/Images/Payments.png" height="200px" width="800px"></kbd><br>

---

## 6. Additional Features

### Additional Options
- Click the "More Options" button to access extra functionalities.
- Add specific amounts (1, 5, 10, 20,50 100) to the "Amount Given" field.
-Exact change button in order to calculate the exact amount of change without having to press two buttons.
-
<img src="https://github.com/SICTCCS/LunchBoxCashRegister/blob/main/Images/ExactChange.png" height="200px" width="800px"></kbd><br>

---

## 7. Troubleshooting

- **Invalid Inputs:** Ensure that you enter valid numerical values in the "Amount Given" field.
- **Negative Total:** If the total displays as negative, review the item quantities and ensure proper usage of the "Del" button.

---

## 8. Resetting the App

To start a new order and reset all quantities:

1. Click the "Reset" button.
2. All item quantities and the total price will be set to zero.

---

## 9. Technical Support

If you encounter technical issues or have questions:

- Contact our technical support team via either email too .
- Provide detailed information about the problem for faster assistance.

---

## 10. System Architecture

### Components
1. **Frontend (Android)**
   - Native Android app written in Java
   - Handles user interface and input validation
   - Communicates with backend via REST API

2. **Backend (Python)**
   - Flask-based REST API
   - Processes transactions
   - Manages database operations
   - Handles automated tasks

3. **Database (MariaDB)**
   - Stores transaction records
   - Maintains user accounts
   - Tracks inventory

4. **Automation (Cron)**
   - Manages daily operation schedule
   - Handles system startup/shutdown
   - Processes end-of-day reports

### Data Flow
1. Cashier inputs order details
2. Android app validates and sends to API
3. API processes and stores in database
4. Automated reports generated daily

Thank you for using the Cash Register App.
