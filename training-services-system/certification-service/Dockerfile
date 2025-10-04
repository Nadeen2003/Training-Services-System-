FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR_FILE=target/certification-service-*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9004
ENTRYPOINT ["java","-jar","/app/app.jar"]
