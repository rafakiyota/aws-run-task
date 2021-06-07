FROM openjdk:11
ARG cluster
ARG task
ARG type
ARG subnetids
ARG securitygroupids
ARG role
ARG region
ENV cluster_env=$cluster
ENV task_env=$task
ENV type_env=$type
ENV subnetids_env=$subnetids
ENV securitygroupids_env=$securitygroupids
ENV role_env=$role
ENV region_env=$region
MAINTAINER Rafael Kiyota
COPY target/*.jar app.jar
WORKDIR /
ENTRYPOINT java -jar app.jar --cluster=$cluster --task=$task --type=$type --subnetids=$subnetids --securitygroupids=$securitygroupids --role=$role --region=$region