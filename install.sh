#!/bin/sh
#install auto script for test tools

#git pull
mvn clean package
sudo cp target/*.war /usr/share/tomcat6/webapps/


