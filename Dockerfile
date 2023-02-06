FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

#Workspace
WORKDIR usr/share/udemy

#Add .jar under target location
ADD target/selenium-docker.jar         selenium-docker.jar
ADD target/selenium-docker-tests.jar   selenium-docker-tests.jar
ADD target/libs                        libs

#Add suitefiles
ADD flight-module.xml                  flight-module.xml
ADD search-module.xml                  search-module.xml

#Add health check script & convert to unix format
ADD healthcheck.sh                     healthcheck.sh

RUN dos2unix healthcheck.sh

#Broswer
#host
#module
ENTRYPOINT sh healthcheck.sh
