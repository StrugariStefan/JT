@echo off
call mvn clean package
call docker build -t ro.uaic.info.laborator2/Laborator2 .
call docker rm -f Laborator2
call docker run -d -p 9080:9080 -p 9443:9443 --name Laborator2 ro.uaic.info.laborator2/Laborator2