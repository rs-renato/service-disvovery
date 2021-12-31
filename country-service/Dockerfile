FROM openjdk:11-jdk
LABEL "maintainer"="renators"
COPY target/*.jar country-service.jar
ENTRYPOINT ["java","-jar","/country-service.jar"]