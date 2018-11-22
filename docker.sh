#!/usr/bin/env bash

#define
projectName=sangoes-uc
# delete docker .jar
echo "delete old jar file"
rm -rf ./dockerfile/uc/${projectName}.jar
# docker
echo "remove docker compose"
read -p "input profile:" profile
docker-compose -f docker-compose-${profile}.yml rm -f ${projectName}

echo "remove docker images"
docker rmi -f sangoes:${projectName}

echo "maven install"
# maven
mvn clean install -DskipTests

echo "copy new jar file to docker"
# copy
cp ./sangoes-uc/target/${projectName}.jar ./dockerfile/uc/

#
echo "docker create network"
docker network create net-sangoes
#docker compose
echo "docker compose"
docker-compose -f docker-compose-${profile}.yml up -d
