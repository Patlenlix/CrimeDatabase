
# CrimeDatabase

![repo_size](https://img.shields.io/github/repo-size/Patlenlix/CrimeDatabase)

## Java Enterprise | ITHS | JU21

A Spring Boot application handling crimes, criminals and victims which is stored in a MySQL database. Different roles have
different degree of access throughout the application.

###  E/R diagram
![ER Diagram](src/main/resources/image/ERdiagram.png)

---

### Done [Live features]

* CRUD functionality for all current entities
* Crime, Criminal, User, Victim entities

### Planned features

* Database relations
* Spring security
* Custom exceptions
* Thymeleaf frontend
* Implementation of JMS
* Various levels of accessibility
* MySQL database
* Dockerfile + Container
* Token system
* Comprehensive unit testing

---

### Deployment

1. Clone/Fork this repo in your favorite IDE
2. Create a .jar file: Run `mvn package`
3. Build the image: Go to the folder of the application and run the following from you CLI:
   `docker image build -t crimedb .`
4. Run the application: `docker container run crimedb`

---

### Endpoints

```
http://localhost:8080
```

