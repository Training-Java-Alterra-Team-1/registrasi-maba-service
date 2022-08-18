FROM openjdk:11
EXPOSE 8081
COPY target/register-0.0.1.jar register-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/register-0.0.1.jar"]