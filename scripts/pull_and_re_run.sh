#!/bin/bash
set -e

export SPRING_APPLICATION_NAME=onboarder
git pull origin
./mvnw clean install -e -DskipTests
docker compose stop
docker compose build
docker compose up -d onboarder
