FROM maven:3.8.5-openjdk-17-slim as build
COPY ./ /src
RUN mvn -f /src/pom.xml clean package

FROM openjdk:17
COPY --from=build /src/target/crime-database.jar /usr/src/crimedatabase/
WORKDIR usr/src/crimedatabase
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "crime-database.jar"]
