#!/bin/bash
echo "Loading db script..."
mysql -uroot -pmysql test < src/test/resources/schema-mysql.sql
echo "Done"
