FROM openjdk:17
COPY ./target/CrimeDatabase-0.0.1-SNAPSHOT.jar /usr/src/crimedatabase/
WORKDIR usr/src/crimedatabase
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "CrimeDatabase-0.0.1-SNAPSHOT.jar"]
