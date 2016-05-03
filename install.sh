#!/bin/bash

ps aux | grep 'java -jar'| awk '{print $2}' | xargs kill -9
mvn -f '/home/ec2-user/builds/Gateway Web Service/pom.xml' clean;
mvn -f '/home/ec2-user/builds/Gateway Web Service/pom.xml' install;
chmod a+w '/home/ec2-user/builds/Gateway Web Service/target'
cp '/home/ec2-user/builds/Gateway Web Service/target/indra-1.0.0-BUILD-SNAPSHOT.war' '/home/ec2-user/apache-tomcat-8.0.33/webapps'
sh '/home/ec2-user/apache-tomcat-8.0.33/bin/startup.sh' 
