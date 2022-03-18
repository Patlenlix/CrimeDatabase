FROM openjdk:17
COPY ./target/crime-database.jar /usr/src/crimedatabase/
WORKDIR usr/src/crimedatabase
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "crime-database.jar"]
