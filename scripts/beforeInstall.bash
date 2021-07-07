#!/bin/bash

if [ -d /home/ec2-user/deploy ]; then
  rm -rf /home/ec2-user/deploy
fi

mkdir -vp /home/ec2-user/deploy

docker ps 2> /dev/null

if [ "$(docker ps 2> /dev/null)" == *"ldntjd1081/apt-server"* ]; then
  docker stop ldntjd1081/apt-server:latest
fi

if [ "$(docker ps -a 2> /dev/null)" == *"ldntjd1081/apt-server"* ]; then
  docker rm -f ldntjd1081/apt-server:latest
fi

if [ "$(docker images -q ldntjd1081/apt-server:latest 2> /dev/null)" == *"ldntjd1081/apt-server"* ]; then
  docker rmi -f $(docker images --format '{{.Repository}}:{{.Tag}}' --filter=reference=ldntjd1081/apt-server:latest)
fi
