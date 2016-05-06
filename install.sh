#!/bin/bash
sudo ps aux | grep 'java -jar'| awk '{print $2}' | xargs kill -9
sudo mvn -f '/home/ec2-user/builds/Auth Service/pom.xml' clean;
sudo mvn -f '/home/ec2-user/builds/Auth Service/pom.xml' install;
sudo cp '/home/ec2-user/builds/Auth Service/target/indra-auth-1.0.0.war' '/home/ec2-user/apache-tomcat-8.0.33/webapps'

sudo mvn -f '/home/ec2-user/builds/Data Service/pom.xml' clean;
sudo mvn -f '/home/ec2-user/builds/Data Service/pom.xml' install;
sudo cp '/home/ec2-user/builds/Data Service/target/indra-dao-1.0.0.war' '/home/ec2-user/apache-tomcat-8.0.33/webapps'

sudo sh '/home/ec2-user/apache-tomcat-8.0.33/bin/startup.sh'
sudo mvn -f '/home/ec2-user/builds/Gateway Web Service/pom.xml' clean;
sudo mvn -f '/home/ec2-user/builds/Gateway Web Service/pom.xml' install;
sudo cp '/home/ec2-user/builds/Gateway Web Service/target/indra-1.0.0-BUILD-SNAPSHOT.war' '/home/ec2-user/apache-tomcat-8.0.33/webapps'