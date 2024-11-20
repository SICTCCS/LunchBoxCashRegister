# API Installation Guide

## Table of Contents
[Overview](#overview)
[Prerequisites](#prerequisites)
[Installation Steps](#installation)
[Database Setup](#database)
[API Configuration](#configuration)
[Testing](#testing)
[Web Interface](#webinterface)
[Troubleshooting](#troubleshooting)

<div id='overview'/>

## Overview
The API component serves as the critical bridge between the Android cash register app and the MariaDB database. It provides:
- Secure transaction processing
- Real-time data validation
- Automated database operations
- Daily reporting functionality

<div id='prerequisites'/>

## Prerequisites
Ensure your Raspberry Pi has:
- Internet connectivity
- At least 2GB free storage
- Raspbian OS installed
- User with sudo privileges

<div id='installation'/>

## Installation Steps

### 1. System Packages
Install required system dependencies:
```console
# Update system packages
sudo apt update

# Install core dependencies
sudo apt install -y python3 vim curl

# Install Python packages
sudo apt install -y python3-flask python3-pymysql
```

### 2. Database Setup
```console
# Install MariaDB
sudo apt install -y mariadb-server

# Start and enable MariaDB service
sudo systemctl start mariadb
sudo systemctl enable mariadb
```

### 3. API Setup
```console
# Clone repository (if not already done)
git clone https://github.com/SICTCCS/LunchBoxCashRegister.git

# Move to project directory
cd LunchBoxCashRegister/API

# Set up Python virtual environment
python3 -m venv venv
source venv/bin/activate

# Install Python dependencies
pip install -r requirements.txt
```

<div id='testing'/>

## Testing the Installation

### 1. API Health Check
```console
# Test API endpoint
curl -s -X POST -H "Content-Type: application/json" \
     -d "@json/add_data.json" http://localhost:8000 | jq .
```

### 2. Sample Transaction Test
Create a test transaction file (add_data.json):
```json
{
    "meal": 1,
    "dessert_side": 1,
    "entree": 1,
    "soup": 1,
    "cookie": 1,
    "roll": 1,
    "description": "test transaction"
}
```

<div id='webinterface'/>

## Web Interface Access

Access the management interface at:
```
http://10.60.4.150:4000
```

Important Notes:
- Always restart the API after downloading CSV files
- Keep database credentials secure
- Monitor logs for errors
- Back up data regularly

<div id='troubleshooting'/>

## Troubleshooting

Common issues and solutions:
1. If API fails to start:
   - Check Python virtual environment is activated
   - Verify MariaDB is running
   - Check port 8000 is available

2. If database connection fails:
   - Verify MariaDB service status
   - Check credentials in config file
   - Ensure database exists

3. If cron jobs aren't running:
   - Check crontab entries
   - Verify file permissions
   - Check system logs