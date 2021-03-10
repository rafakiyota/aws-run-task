FROM openjdk:11
ARG cluster
ARG task
ARG type
ARG subnetids
ARG securitygroupids
ENV cluster_env=$cluster
ENV task_env=$task
ENV type_env=$type
ENV subnetids_env=$subnetids
ENV securitygroupids_env=$securitygroupids
MAINTAINER Rafael Kiyota
COPY target/*.jar app.jar
WORKDIR /
ENTRYPOINT java -jar app.jar --cluster=$cluster --task=$task --type=$type --subnetids=$subnetids --securitygroupids=$securitygroupids