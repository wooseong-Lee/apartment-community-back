#!/bin/bash

if [ -d /home/ec2-user/deploy ]; then
  rm -rf /home/ec2-user/deploy
fi

if [ "$(docker ps -q)" != "" ]; then
  docker stop `docker ps -q`
fi

if [ "$(docker ps -a -q)" != "" ]; then
  docker rm -f `docker ps -a -q`
fi

if [ "$(docker images -q)" != "" ]; then
  docker rmi -f `docker images -q`
fi
