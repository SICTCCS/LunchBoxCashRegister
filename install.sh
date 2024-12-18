#!/bin/bash

# Exit on any error
set -e

# Configuration
INSTALL_DIR="/home/lb/Documents/LunchBoxCashRegister"
API_DIR="${INSTALL_DIR}/API"
VENV_DIR="${API_DIR}/venv"
LOG_FILE="${API_DIR}/install.log"
DB_ROOT_PASSWORD="Password1"  # Changed to Password1

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
apt install -y mariadb-server cron python3-full python3-pip python3-venv vim curl python3-flask python3-pymysql

# Set up Python virtual environment with system packages
log "Creating Python virtual environment..."
python3 -m venv "${VENV_DIR}" --system-site-packages
source "${VENV_DIR}/bin/activate"

log "Python packages already installed via apt"

# Configure MariaDB
log "Configuring MariaDB..."
systemctl start mariadb
systemctl enable mariadb

# Secure MariaDB installation and set root password
log "Securing MariaDB installation..."
mysql -u root <<EOF
-- Set root password
ALTER USER 'root'@'localhost' IDENTIFIED BY '${DB_ROOT_PASSWORD}';
-- Remove anonymous users
DELETE FROM mysql.user WHERE User='';
-- Disallow root login remotely
DELETE FROM mysql.user WHERE User='root' AND Host NOT IN ('localhost', '127.0.0.1', '::1');
-- Remove test database
DROP DATABASE IF EXISTS test;
DELETE FROM mysql.db WHERE Db='test' OR Db='test\\_%';
-- Reload privilege tables
FLUSH PRIVILEGES;
EOF

# Initialize database
log "Initializing database..."
MYSQL_PWD="${DB_ROOT_PASSWORD}" "${VENV_DIR}/bin/python3" "${INSTALL_DIR}/DBSetup.py"

# Configure cron jobs with proper Python path
log "Setting up cron jobs..."
crontab_content="
# LunchBox Cash Register Automation
01 06 * * 1-5 python3 ${API_DIR}/Web/webRun.py
01 10 * * 1-5 python3 ${API_DIR}/index5.py 
30 15 * * 1-5 kill -f ${API_DIR}/index5.py 
30 16 * * 1-5 kill -f ${API_DIR}/Web/webRun.py"

echo "${crontab_content}" | crontab -

log "Installation completed successfully!"
log "Web interface will be available at http://localhost:4000"

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

# Print important information
log "Installation completed. Important information:"
log "Database root password: ${DB_ROOT_PASSWORD}"
log "Please change this password in production!"
log "To connect to MariaDB: mysql -u root -p"
