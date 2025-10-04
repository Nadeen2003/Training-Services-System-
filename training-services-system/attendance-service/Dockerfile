FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR_FILE=target/attendance-service-*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9003
ENTRYPOINT ["java","-jar","/app/app.jar"]
