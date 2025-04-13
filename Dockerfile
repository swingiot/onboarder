FROM eclipse-temurin:21-jre-alpine
ARG JAR_FILE=target/*-SNAPSHOT.jar
COPY ${JAR_FILE} onboarder.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker","-jar","/onboarder.jar"]
