#!/bin/bash

if [ -d /home/ec2-user/deploy ]; then
  rm -rf /home/ec2-user/deploy
fi

mkdir -vp /home/ec2-user/deploy

docker stop apt-server:latest
docker rm `docker ps -a -q`

if [[ "$(docker images -q ldntjd1081/apt-server:latest 2> /dev/null)" != ""]]; then
  docker rmi -f $(docker images --format '{{.Repository}}:{{.Tag}}' --filter=reference=ldntjd1081/apt-server:latest)
fi
