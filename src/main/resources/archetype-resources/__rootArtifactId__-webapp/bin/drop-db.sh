#!/bin/bash
echo "Dropping db..."
mysql -uroot -pmysql test < src/test/resources/schema-drop-mysql.sql
echo "Done"
