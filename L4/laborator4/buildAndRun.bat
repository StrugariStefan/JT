@echo off
call mvn clean package
call docker build -t ro.uaic.info/laborator3 .
call docker rm -f laborator3
call docker run -d -p 9080:9080 -p 9443:9443 --name laborator3 ro.uaic.info/laborator3