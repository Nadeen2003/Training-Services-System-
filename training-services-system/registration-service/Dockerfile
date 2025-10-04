FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR_FILE=target/registration-service-*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","/app/app.jar"]
