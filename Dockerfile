FROM openjdk:11
ARG cluster
ARG task
ENV cluster_name=$cluster
ENV task_name=$task
MAINTAINER Rafael Kiyota
COPY /target/aws-run-task-0.0.1-SNAPSHOT.jar /
WORKDIR /
ENTRYPOINT java -jar aws-run-task-0.0.1-SNAPSHOT.jar --cluster=$cluster --task=$task