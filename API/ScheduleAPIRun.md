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

Here is a example of what the file should look like

[Click Here](file.txt)



Save the file and it should work automatically 

## Debugging tips

Identify the Process ID (PID): 

First, identify the process ID (PID) of the running Python script using the pgrep command. Replace "your_script.py" with the actual name of your script.

```console
pgrep -f index5.py
```

This command will output the PID of the running Python script.

Kill the Process:

Once you have the PID, use the kill -9 command to forcefully terminate the process:

```console
sudo kill -9 PID
```

Replace "PID" with the actual process ID you obtained in the previous step.