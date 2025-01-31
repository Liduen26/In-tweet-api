#!/bin/sh

if [ "$INIT_DB" = true ]; then
    echo "Database initialisation..."
    mariadb -h "$DB_HOST" -P"$DB_PORT" -u "$DB_USER" -p"$DB_PSW" "$DB_NAME" < init.sql
    echo "Database loaded !"
fi


java -jar in-tweet-api.jar --spring.profiles.active=prod
