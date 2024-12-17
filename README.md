# Cash Register App User Manual

## Table of Contents
1. [Introduction](#1-introduction)
2. [Installation](#2-installation)
3. [Getting Started](#3-getting-started)
4. [Ordering Items](#4-ordering-items)
5. [Troubleshooting](#7-troubleshooting)
6. [System Architecture](#10-system-architecture)

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
- Each addition increases the total price.
  
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

## 5. Troubleshooting

- If it gives an error to see Mr. Bander then that means the python file isn't running.
- Other errors are potnetial setup errors or missing packages on install

---

## 6. System Architecture

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
