#!/bin/sh
mvn clean package && docker build -t ro.uaic.info/laborator3 .
docker rm -f laborator3 || true && docker run -d -p 9080:9080 -p 9443:9443 --name laborator3 ro.uaic.info/laborator3