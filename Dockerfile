FROM eclipse-temurin:21-jre
WORKDIR /scout-finance
COPY target/scout-finance.jar scout-finance.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
