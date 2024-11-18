FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/api-gateway-0.0.1.jar
COPY ${JAR_FILE} app-gateway.jar
EXPOSE 2222
ENTRYPOINT ["java","-jar","/app-gateway.jar"]
