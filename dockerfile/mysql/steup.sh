#!/usr/bin/env bash
set -e

echo 'service mysql status'

echo '1.start mysql..'

service mysql start

sleep 3

echo 'service mysql status'

echo 'show databases'

DATABASES=$(mysql -e "show databases")
DATABASE="boot"
echo ${DATABASES}
echo ${DATABASE}
if [[ "$DATABASES" =~ "$DATABASE" ]];then

echo '----------mysql restart----------'
echo '2.data is already exit,not init...'

else
echo '-----------------mysql first start------'
echo '2.start into data......'

mysql < /mysql/boot.sql
echo '3.finally into data'

sleep 3

echo 'service mysql status'
echo 'mysql start com, ok-----'

fi
tail -f /dev/null




