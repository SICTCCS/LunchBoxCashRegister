# LunchBox Cash Register Setup Guide

## Initial Setup

1. First, make the installation script executable:
```bash
chmod +x install.sh
```

2. Run the installation script:
```bash
sudo ./install.sh
```

This will:
- Install required packages
- Set up the MariaDB database
- Configure the Python environment
- Set up automated scheduling

3. Check and make sure the cron jobs are set up correctly:
```bash
crontab -e
```
If the crontab file does not open and it prints to the terminal, select the editor vim.basic.

and make sure the following lines are present:
```bash
01 06 * * 1-5 python3 home/lb/LunchBoxCashRegister/API/Web/webRun.py
01 10 * * 1-5 python3 home/lb/LunchBoxCashRegister/API/index5.py 
30 15 * * 1-5 kill -f home/lb/LunchBoxCashRegister/API/index5.py 
30 16 * * 1-5 kill -f home/lb/LunchBoxCashRegister/API/Web/webRun.py
```


## Running the Application

The app and website are configured to run automatically on school days, but if you want to run them manually:

### To Run the Cash Register API:
```bash
python3 API/index5.py
```
This will start the API server on port 8000.

### To Run the Web Interface:
```bash
python3 API/Web/webRun.py
```
This will start the web interface on port 4000.

## Important Notes

- The automated schedule will start tomorrow:
  - Web interface starts at 6:01 AM
  - Cash register API starts at 10:01 AM
  - API stops at 3:30 PM
  - Web interface stops at 4:30 PM

- The API runs on port 8000 and the web interface runs on port 4000
- Make sure to stop any manually started services before the automated schedule begins
- The database password is set to 'Password1' - change this in production

## Troubleshooting

If you encounter any issues:
1. Check the log files in the API directory
2. Verify MariaDB is running: `sudo systemctl status mariadb`
3. Ensure ports are available: 
   - For API: `sudo lsof -i :8000`
   - For Web: `sudo lsof -i :4000`