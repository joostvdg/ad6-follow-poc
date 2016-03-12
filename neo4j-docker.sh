#!/usr/bin/env bash

NAME="ad6neo4j"
IMAGE="neo4j:2.3.2"
NETWORK_DEFAULT="ad6"
NETWORK="${1:-$NETWORK_DEFAULT}"

HOME_DEFAULT="/home/joost/Projects-Data/"
HOMEDIR="${2:-$HOME_DEFAULT}"

RUNNING=`docker ps | grep -c $NAME`
if [ $RUNNING -gt 0 ]
then
   echo "Stopping $NAME"
   docker stop $NAME
fi

EXISTING=`docker ps -a | grep -c $NAME`
if [ $EXISTING -gt 0 ]
then
   echo "Removing $NAME"
  docker rm $NAME
fi

echo "Create new instance $NAME based on $IMAGE"
docker run 	--name $NAME \
            --publish=7474:7474 \
			-v $HOMEDIR/ad6/neo4j:/data \
       		--net=$NETWORK \
       		--net-alias=$NAME \
       		--ulimit=nofile=40000:40000 \
			-d $IMAGE


echo "Tail the logs of the new instance"
sleep 10
docker logs $NAME

