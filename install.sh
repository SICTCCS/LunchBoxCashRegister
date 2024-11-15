#!/bin/bash

# Update package information
sudo apt update

# Install required system packages
sudo apt install -y mariadb-server cron python3-full python3-pip python3-venv vim curl

# Create virtual environment
python3 -m venv "/home/lb/Documents/LunchBoxCashRegister/API/venv"

# Install Python packages in virtual environment
source "/home/lb/Documents/LunchBoxCashRegister/API/venv/bin/activate"
pip3 install flask pymysql

# Start MariaDB service
sudo systemctl start mariadb
sudo systemctl enable mariadb

# Run database setup script
"/home/lb/Documents/LunchBoxCashRegister/API/venv/bin/python3" "/home/lb/Documents/LunchBoxCashRegister/DBSetup.py"

# Clear existing crontab and set up new cron jobs
echo "01 06 * * 1-5 /home/lb/Documents/LunchBoxCashRegister/API/Web/webRun.py
01 10 * * 1-5 /home/lb/Documents/LunchBoxCashRegister/API/index5.py 
30 15 * * 1-5 pkill -f /home/lb/Documents/LunchBoxCashRegister/API/index5.py 
30 16 * * 1-5 pkill -f /home/lb/Documents/LunchBoxCashRegister/API/Web/webRun.py" | crontab -

echo "All dependencies have been installed and the automation cron job has been set up."


# Get the directory where the script is located
# SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# python3 -m venv "${SCRIPT_DIR}/venv"
# source "${SCRIPT_DIR}/venv/bin/activate"
# "${SCRIPT_DIR}/venv/bin/python3" "${SCRIPT_DIR}/DBSetup.py"
# echo "01 06 * * 1-5 ${SCRIPT_DIR}/venv/bin/python3 ${SCRIPT_DIR}/API/Web/webRun.py
# 01 10 * * 1-5 ${SCRIPT_DIR}/venv/bin/python3 ${SCRIPT_DIR}/API/index5.py 
# 30 15 * * 1-5 pkill -f ${SCRIPT_DIR}/API/index5.py 
# 30 16 * * 1-5 pkill -f ${SCRIPT_DIR}/API/Web/webRun.py" | crontab -