FROM alpine/git as clone

WORKDIR /app/bin

RUN git clone https://github.com/nitianyi0123/asuna.git


FROM maven:3.5-jdk-8-alpine as build

WORKDIR /app/bin

RUN mvn install


FROM openjdk:8

MAINTAINER nitianyi <n121180075@gmail.com>

ENV logDir /app/log

# 时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo Asia/Shanghai > /etc/timezone

WORKDIR /app/bin

COPY target/asuna.jar ./app.jar

ENTRYPOINT ["java","-jar","/app/bin/app.jar"]

EXPOSE 8080