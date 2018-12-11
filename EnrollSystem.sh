#!/bin/bash
#

dbUser='root'
dbPasswd='admin'
dbHost='www.yuanmaxinxi.com'
dbName='enroll'

mysql -u$dbUser -p$dbPasswd -h$dbHost -D$dbName < ./EnrollSystem.sql
