#!/bin/sh
mvn clean package && docker build -t ro.uaic.info/laborator7 .
docker rm -f laborator7 || true && docker run -d -p 9080:9080 -p 9443:9443 --name laborator7 ro.uaic.info/laborator7