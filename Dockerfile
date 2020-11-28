FROM openjdk:8

MAINTAINER nitianyi <n121180075@gmail.com>

# 时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo Asia/Shanghai > /etc/timezone

WORKDIR /app/bin

COPY target/asuna.jar ./app.jar

ENTRYPOINT ["java","-jar","/app/bin/app.jar"]

EXPOSE 8080