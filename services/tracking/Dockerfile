FROM openjdk:22-oracle

WORKDIR /app

COPY target/tracking-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8092

ENTRYPOINT ["java", "-jar", "app.jar"]