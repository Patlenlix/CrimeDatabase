# CrimeDatabase
![Release](https://img.shields.io/github/v/release/Patlenlix/CrimeDatabase)
![repo_size](https://img.shields.io/github/repo-size/Patlenlix/CrimeDatabase)


## Java Enterprise | ITHS | JU21

A Spring Boot application handling crimes, criminals and victims which is stored in a MySQL database. Different roles
have different degree of access throughout the application.

### E/R diagram

![ER Diagram](src/main/resources/static/images/ERdiagram.png)

---

### Done [Live features]

* CRUD functionality for all current entities
* Crime, Criminal, User, Victim, Category and Address entities
* Database relations
* Dockerfile + Container
* Custom exceptions
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/1)
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/2)
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/5)

### Planned features

* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/3)
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/4)
* MySQL database

#### Check out the [ROADMAP](https://github.com/orgs/Patlenlix/projects/1/views/1)

---

### Deployment

1. Clone/Fork this repo in your favorite IDE
    - Go to the folder where you want the application to save 
    - Run the following from your Console: `git clone https://github.com/Patlenlix/CrimeDatabase.git`
3. Install Docker Desktop
4. Run the application:
    - Go to the folder of the application 
    - Run the following from your Console: `docker-compose up`
5. Use Insomnia to run the endpoints below
    - Use Basic Auth:
        - Admin:
            - Username: admin
            - Password: admin123
        - User:
            - Username: user
            - Password: user123
6. To access RabbitMQ console to manage messaging service
    - Go to: `http://localhost:15672`
        - Username: guest
        - Password: guest

---

### Endpoints

All URLs start with `http://localhost:8080`

#### Category:

| HTTP-verb | URL              | Authorization           | Info                            |
|-----------|------------------|-------------------------|---------------------------------|
| POST      | /categories      | All authenticated users | Creates category                |
| DELETE    | /categories/{id} | All authenticated users | Deletes category with id = {id} |
| GET       | /categories/{id} | All authenticated users | Returns category with id = {id} |
| GET       | /categories      | All authenticated users | Returns all categories          |
| PUT       | /categories/{id} | All authenticated users | Updates category with id = {id} |

POST and PUT needs a Body with a JSON object. Example of body for POST (PUT also needs id):

```json
{
  "name": "Theft"
}
```

#### Victim:

| HTTP-verb | URL             | Authorization                       | Info                          |
|-----------|-----------------|-------------------------------------|-------------------------------|
| POST      | /victims        | Authenticated users with role ADMIN | Creates victim                |
| DELETE    | /victims/{id}   | Authenticated users with role ADMIN | Deletes victim with id = {id} |
| GET       | /victims/{id}   | Authenticated users with role ADMIN | Returns victim with id = {id} |
| GET       | /victims        | Authenticated users with role ADMIN | Returns all victims           |
| PUT       | /victims/{id}   | Authenticated users with role ADMIN | Updates victim with id = {id} |

POST and PUT needs a Body with a JSON object. Example of body for POST (PUT also needs id):

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "2000-01-01"
}
```

#### Criminal:

| HTTP-verb | URL             | Authorization                       | Info                            |
|-----------|-----------------|-------------------------------------|---------------------------------|
| POST      | /criminals      | Authenticated users with role ADMIN | Creates criminal                |
| DELETE    | /criminals/{id} | Authenticated users with role ADMIN | Deletes criminal with id = {id} |
| GET       | /criminals/{id} | Authenticated users with role ADMIN | Returns criminal with id = {id} |
| GET       | /criminals      | Authenticated users with role ADMIN | Returns all criminals           |
| PUT       | /criminals/{id} | Authenticated users with role ADMIN | Updates criminal with id = {id} |

POST and PUT needs a Body with a JSON object. Example of body for POST (PUT also needs id):

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "2000-01-01"
}
```

#### Address:

| HTTP-verb | URL                | Authorization           | Info                           |
|-----------|--------------------|-------------------------|--------------------------------|
| POST      | /addresses         | All authenticated users | Creates address                |
| DELETE    | /addresses/{id}    | All authenticated users | Deletes address with id = {id} |
| GET       | /addresses/{id}    | All authenticated users | Returns address with id = {id} |
| GET       | /addresses         | All authenticated users | Returns all addresses          |
| PUT       | /addresses/{id}    | All authenticated users | Updates address with id = {id} |

POST and PUT needs a Body with a JSON object. Example of body for POST (PUT also needs id):

```json
{
  "city": "Gothenburg",
  "zipCode": "41324",
  "streetAddress": "Street 1"
}
```

#### Crime:

| HTTP-verb | URL              | Authorization           | Info                           |
|-----------|------------------|-------------------------|--------------------------------|
| POST      | /crimes          | All authenticated users | Creates crime                  |
| DELETE    | /crimes/{id}     | All authenticated users | Deletes crime with id = {id}   |
| GET       | /crimes/{id}     | All authenticated users | Returns crime with id = {id}   |
| GET       | /crimes          | All authenticated users | Returns all crimes             |
| PUT       | /crimes/{id}     | All authenticated users | Updates crime with id = {id}   |

POST and PUT needs a Body with a JSON object. Example of body for POST (PUT also needs id):

```json
{
  "name": "Example crime",
  "time": "2022-03-18 15:48"
}
```

#### Publish RabbitMQ Messaging Service:

| HTTP-verb | URL      | Authorization           | Info                                 |
|-----------|----------|-------------------------|--------------------------------------|
| POST      | /publish | All authenticated users | Sends message internally to Listener |

POST needs a Body with a JSON object. Example of body for POST:

```json
{
  "message": "Sample message"
}
```
