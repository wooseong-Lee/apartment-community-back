#!/bin/bash

if [ -d /home/ec2-user/deploy ]; then
  rm -rf /home/ec2-user/deploy
fi

y | docker system prune -a
