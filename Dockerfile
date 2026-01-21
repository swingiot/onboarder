FROM eclipse-temurin:24-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} onboarder.jar
ENTRYPOINT ["java", "-jar", "/onboarder.jar"]
