#!/usr/bin/env bash
echo "delete old jar file"
# delete docker .jar
rm -rf ./sangoes-uc/src/docker/sangoes-uc.jar
# docker

echo "maven install"
# maven
mvn clean install -DskipTests

echo "copy new jar file to docker"
# copy
copy ./sangoes-uc/target/sangoes-uc.jar ./sangoes-uc/src/docker/

echo "docker build"
# docker build
docker:build -f pom.xml

echo "docker compose"
#docker compose
docker-compose up