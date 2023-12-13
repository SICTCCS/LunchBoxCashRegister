<div id='overview'/>

## Overview
Instructions to schedule the api to run at a certain time and stop at a certian time

## Installing Properties 
First you will need to load up crontab

```console
crontab -e
```

Go the the last line and enter both the codes below 
This means minute hour day month day_of_week command_to_be_executed
After the > it logs what it does, this is to debug

This is to run the api at 10:01

```console
01 10 * * 1-5 /usr/bin/python3 /home/pi/Documents/web/api/py/src/iotapi/index5.py  > /home/pi/Documents/output.log 2>&1
```

This is to stop the api at 3:30

```console
30 15 * * 1-5 pkill -f /home/pi/Documents/web/api/py/src/iotapi/index5.py  > /home/pi/Documents/output.log 2>&1
```

