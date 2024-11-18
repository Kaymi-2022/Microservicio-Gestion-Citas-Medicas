FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/gestionar-usuarios-0.0.1.jar
COPY ${JAR_FILE} app-gestionar-usuarios.jar
EXPOSE 3333
ENTRYPOINT ["java","-jar","/app-gestionar-usuarios.jar"]
