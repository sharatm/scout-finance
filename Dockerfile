FROM eclipse-temurin:21-jre
WORKDIR /scout-finance
COPY *.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]