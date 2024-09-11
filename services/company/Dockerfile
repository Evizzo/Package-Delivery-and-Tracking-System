FROM openjdk:22-oracle

WORKDIR /app

COPY target/company-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]