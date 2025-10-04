FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR_FILE=target/course-service-*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","/app/app.jar"]
