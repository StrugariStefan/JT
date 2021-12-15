@echo off
call mvn clean package
call docker build -t ro.uaic.info/laborator7 .
call docker rm -f laborator7
call docker run -d -p 9080:9080 -p 9443:9443 --name laborator7 ro.uaic.info/laborator7