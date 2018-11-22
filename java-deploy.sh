#!/usr/bin/env bash
#define
projectName=sangoes-uc
# delete docker .jar
echo "delete old jar file"
rm -rf ./dockerfile/uc/${projectName}.jar


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
