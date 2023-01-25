FROM openjdk:19
ARG JAR_FILE=target/us22po_cs_service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]