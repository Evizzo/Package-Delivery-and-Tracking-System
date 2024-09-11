FROM openjdk:22-oracle

WORKDIR /app

COPY target/post-office-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar", "app.jar"]