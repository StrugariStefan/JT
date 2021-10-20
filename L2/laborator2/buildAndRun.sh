#!/bin/sh
mvn clean package && docker build -t ro.uaic.info.laborator2/Laborator2 .
docker rm -f Laborator2 || true && docker run -d -p 9080:9080 -p 9443:9443 --name Laborator2 ro.uaic.info.laborator2/Laborator2