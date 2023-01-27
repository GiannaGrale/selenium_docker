#!/bin/bash

docker run --rm --name maven -v $PWD/target:/app/target/ -v $PWD/allure-report:/app/allure-report/ hanna369/docker-demo:latest site