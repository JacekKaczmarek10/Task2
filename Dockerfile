FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /build
COPY src/main/java .
RUN mvn clean install

FROM openjdk:21
COPY --from=build /build/target/mvp-0.0.1-SNAPSHOT.jar /usr/local/lib/mvp-0.0.1-SNAPSHOT.jar
EXPOSE 8064
CMD ["java", "-jar", "/usr/local/lib/mvp-0.0.1-SNAPSHOT.jar"]