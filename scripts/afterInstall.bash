#!/bin/bash

docker pull ldntjd1081/apt-server:latest
docker run -it -p 8080:8080 -p 3306:3306 --name apt-server ldntjd1081/apt-server:latest
