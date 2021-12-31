FROM openjdk:11-jdk
LABEL "maintainer"="renators"
COPY target/*.jar eureka-server.jar
ENTRYPOINT ["java","-jar","/eureka-server.jar"]