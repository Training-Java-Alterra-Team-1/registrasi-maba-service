FROM openjdk:8
EXPOSE 8081
COPY target/student-service.jar student-service.jar
ENTRYPOINT ["java", "-jar", "/student-service.jar"]