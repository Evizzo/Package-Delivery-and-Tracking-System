FROM openjdk:22-oracle

WORKDIR /app

COPY target/carrier-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8094

ENTRYPOINT ["java", "-jar", "app.jar"]