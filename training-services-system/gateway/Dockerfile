FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR_FILE=target/gateway-*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 19020
ENTRYPOINT ["java","-jar","/app/app.jar"]
