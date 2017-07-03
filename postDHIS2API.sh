#!/bin/bash

echo -n 'Enter Enter Root API Folder Path: '
read API_HOME;

echo -n 'Enter Enter DHIS2 API Root Url: '
read API_URL;

echo -n 'Enter DHIS2 Instance Username: '
read USERNAME

echo -n 'Enter DHIS2 Instance Password: '
read -s PASSWORD

LOG_FILE=postDHIS2APILog.txt

if [ -z "$USERNAME" ] || [ -z "$PASSWORD" ] ||  [ -z "$API_URL" ] || [ -z "$API_HOME" ] || [ ! -d "$API_HOME" ]; then
    exit 1
fi

echo "*************";
API_FILES=`find $API_HOME -name '*.json'`

rm $LOG_FILE
touch $LOG_FILE
find $API_HOME -name '*.json' | while read API_FILE; do
    END_POINT=${API_FILE#*$API_HOME/}

    curl -k -d @$API_FILE $API_URL/${END_POINT%/*} -H "Content-Type:application/json" -u $USERNAME:$PASSWORD >> $LOG_FILE
    echo "" >> $LOG_FILE
    echo "" >> $LOG_FILE
done