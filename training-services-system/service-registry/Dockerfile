FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR_FILE=target/service-registry-*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app/app.jar"]
