FROM openjdk:11
ARG cluster
ARG task
ARG type
ENV cluster_name=$cluster
ENV task_name=$task
ENV type_env=$type
MAINTAINER Rafael Kiyota
COPY target/*.jar app.jar
WORKDIR /
ENTRYPOINT java -jar app.jar --cluster=$cluster --task=$task --type=$type