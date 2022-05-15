#!/bin/bash

docker build -t jenkins-with-docker --build-arg DOCKER_GRP=`getent group docker | cut -d: -f3` .

