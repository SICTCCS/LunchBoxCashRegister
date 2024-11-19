#!/bin/bash

# Exit on any error
set -e

# Configuration
INSTALL_DIR="/home/lb/Documents/LunchBoxCashRegister"
API_DIR="${INSTALL_DIR}/API"
VENV_DIR="${API_DIR}/venv"
LOG_FILE="${API_DIR}/install.log"

# Logging function
log() {
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] $1" | tee -a "${LOG_FILE}"
}

# Error handling
handle_error() {
    log "Error occurred in install.sh at line $1"
    exit 1
}
trap 'handle_error $LINENO' ERR

# Check if running as root
if [[ $EUID -ne 0 ]]; then
    log "This script must be run as root"
    exit 1
fi

# Create install directory if it doesn't exist
mkdir -p "${INSTALL_DIR}"
log "Created installation directory: ${INSTALL_DIR}"

# Update and install system packages
log "Updating package information..."
apt update

log "Installing system dependencies..."
apt install -y mariadb-server cron python3-full python3-pip python3-venv vim curl

# Set up Python virtual environment
log "Creating Python virtual environment..."
python3 -m venv "${VENV_DIR}"
source "${VENV_DIR}/bin/activate"

log "Installing Python packages..."
pip3 install flask pymysql

# Configure MariaDB
log "Configuring MariaDB..."
systemctl start mariadb
systemctl enable mariadb

# Initialize database
log "Initializing database..."
"${VENV_DIR}/bin/python3" "${INSTALL_DIR}/DBSetup.py"

# Configure cron jobs
log "Setting up cron jobs..."
crontab_content="
# LunchBox Cash Register Automation
01 06 * * 1-5 ${API_DIR}/Web/webRun.py
01 10 * * 1-5 ${API_DIR}/index5.py 
30 15 * * 1-5 pkill -f ${API_DIR}/index5.py 
30 16 * * 1-5 pkill -f ${API_DIR}/Web/webRun.py"

echo "${crontab_content}" | crontab -

log "Installation completed successfully!"
log "Web interface will be available at http://localhost:8000"

# Verify installation
if systemctl is-active --quiet mariadb; then
    log "MariaDB is running correctly"
else
    log "WARNING: MariaDB is not running"
fi

if crontab -l >/dev/null 2>&1; then
    log "Cron jobs configured successfully"
else
    log "WARNING: Cron configuration failed"
fi
