FROM openjdk:11
ARG cluster
ARG task
ENV cluster_name=$cluster
ENV task_name=$task
MAINTAINER Rafael Kiyota
COPY target/*.jar app.jar
WORKDIR /
ENTRYPOINT java -jar app.jar --cluster=$cluster --task=$task