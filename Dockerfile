FROM adoptopenjdk/openjdk16:alpine AS build

USER root

ENV DEPLOY_HOME=/home/ec2-user/deploy/server
WORKDIR $DEPLOY_HOME
COPY . $DEPLOY_HOME/

RUN ./gradlew build --no-build-cache

FROM adoptopenjdk/openjdk16:alpine-jre

ENV DEPLOY_HOME=/home/ec2-user/deploy/server
WORKDIR $DEPLOY_HOME

COPY --from=build $DEPLOY_HOME/build/libs/apt-talk.jar $DEPLOY_HOME/apt-talk.jar

USER root

ENTRYPOINT exec java -jar apt-talk.jar
