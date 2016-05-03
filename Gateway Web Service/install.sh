#!/bin/bash

ps aux | grep 'java -jar'| awk '{print $2}' | xargs kill -9
mvn -f /home/ec2-user/builds/Gateway\ Web\ Service/pom.xml clean;
mvn -f /home/ec2-user/builds/Gateway\ Web\ Service/pom.xml install;
chmod a+w /home/ec2-user/builds/Gateway\ Web\ Service/target
sudo -u ec2-user java -jar /home/ec2-user/builds/Gateway\ Web\ Service/target/indra-1.0.0-BUILD-SNAPSHOT.war &