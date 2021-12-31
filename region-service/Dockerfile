FROM openjdk:11-jdk
LABEL "maintainer"="renators"
COPY target/*.jar region-service.jar
ENTRYPOINT ["java","-jar","/region-service.jar"]