# CrimeDatabase

![Release](https://img.shields.io/github/v/release/Patlenlix/CrimeDatabase)
![repo_size](https://img.shields.io/github/repo-size/Patlenlix/CrimeDatabase)

## Java Enterprise | ITHS | JU21

A Spring Boot application handling crimes, criminals and victims which is stored in a H2 database. The application both
contains an API with endpoints for CRUD operations, which are secured with http basic auth (all demand that you are an
Admin user). Theses can be accessed from other programs, e.g. insomnia. It also contains a frontend using Thymeleaf,
which is secured with form based auth. In this case different roles have different degree of access throughout the
application.

## 1. E/R diagram

![ER Diagram](src/main/resources/static/images/ERdiagram.png)

---

## 2. Features

### Live features

* CRUD functionality for all current entities
* Crime, Criminal, User, Victim, Category and Address entities
* Database relations
* Dockerfile / Docker compose
* Custom exceptions
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/1)
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/2)
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/5)
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/3)
* ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/Patlenlix/CrimeDatabase/4)

### Planned features

* MySQL database
* Logging

#### Check out the [ROADMAP](https://github.com/orgs/Patlenlix/projects/1/views/1)

---

## 3. Deployment

### Alternative 1: Download/Clone Repository

- Download the [Latest Release](https://github.com/Patlenlix/CrimeDatabase/releases)
    - Unzip the compressed file where you want to save the application

**OR**

- Clone the repository
    - Go to the folder where you want the application to save
    - Run the following from your Console: `git clone https://github.com/Patlenlix/CrimeDatabase.git`

**RUN APPLICATION: ALTERNATIVE 1**

1. Install [Docker Desktop](https://www.docker.com/products/docker-desktop/) (and run it)
2. Go to the folder of the application
    - Run the following from your Console: `docker-compose up`(If it doesn't work the first time, try to run it again).

### Alternative 2: Download docker image

1. Install [Docker Desktop](https://www.docker.com/products/docker-desktop/) (and run it)
2. Create a `docker-compose.yml` file that looks like this:

```
version: '3.8'

services:
  backend:
    image: ghcr.io/patlenlix/crimedatabase:latest
    ports:
      - "8080:8080"
    environment:
      - SPRING_RABBITMQ_HOST=rabbitmq
    depends_on:
      - rabbitmq

  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    restart: always
    ports:
      - "15672:15672"
      - "5672:5672"
```

**RUN APPLICATION: ALTERNATIVE 2**

1. Go to the folder of the `docker-compose.yml` file
    - Run the following from your Console: `docker-compose up`

### How to Interact with the application (both Alternatives)

1. Use `Insomnia` to run the REST endpoints below
    - Uses `Basic Auth`
2. Use a `browser` to run the Thymeleaf endpoints below
    - Uses `Form Based Auth`

##### There are two register users when you start the application:

- **Admin**:
    - Username: admin
    - Password: admin123
- **User**:
    - Username: user
    - Password: user123

To access `RabbitMQ` console to manage messaging service

- Go to: `http://localhost:15672`
    - Username: guest
    - Password: guest

---

## 4. Endpoints for REST API

All URLs for our REST API start with `http://localhost:8080/api`

#### Category:

| HTTP-verb | URL              | Authorization                        | Info                            |
|-----------|------------------|--------------------------------------|---------------------------------|
| POST      | /categories      | Authenticated users with role ADMIN  | Creates category                |
| DELETE    | /categories/{id} | Authenticated users with role ADMIN  | Deletes category with id = {id} |
| GET       | /categories/{id} | Authenticated users with role ADMIN  | Returns category with id = {id} |
| GET       | /categories      | Authenticated users with role ADMIN  | Returns all categories          |
| PUT       | /categories/{id} | Authenticated users with role ADMIN  | Updates category with id = {id} |

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

| HTTP-verb | URL                | Authorization                       | Info                           |
|-----------|--------------------|-------------------------------------|--------------------------------|
| POST      | /addresses         | Authenticated users with role ADMIN | Creates address                |
| DELETE    | /addresses/{id}    | Authenticated users with role ADMIN | Deletes address with id = {id} |
| GET       | /addresses/{id}    | Authenticated users with role ADMIN | Returns address with id = {id} |
| GET       | /addresses         | Authenticated users with role ADMIN | Returns all addresses          |
| PUT       | /addresses/{id}    | Authenticated users with role ADMIN | Updates address with id = {id} |

POST and PUT needs a Body with a JSON object. Example of body for POST (PUT also needs id):

```json
{
  "city": "Gothenburg",
  "zipCode": "41324",
  "streetAddress": "Street 1"
}
```

#### Crime:

| HTTP-verb | URL              | Authorization                        | Info                           |
|-----------|------------------|--------------------------------------|--------------------------------|
| POST      | /crimes          | Authenticated users with role ADMIN  | Creates crime                  |
| DELETE    | /crimes/{id}     | Authenticated users with role ADMIN  | Deletes crime with id = {id}   |
| GET       | /crimes/{id}     | Authenticated users with role ADMIN  | Returns crime with id = {id}   |
| GET       | /crimes          | Authenticated users with role ADMIN  | Returns all crimes             |
| PUT       | /crimes/{id}     | Authenticated users with role ADMIN  | Updates crime with id = {id}   |

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

---

## 4. Endpoints for Thymeleaf

All URLs for our Thymeleaf application start with `http://localhost:8080`

#### Login page

If you are not logged in, and you try to access any of the endpoint that are secured with form based aut, you will be
redirected to the login-page. This page is open for everyone.

| HTTP-verb | URL    | Authorization           | Info                                                                                                                                                                                                                                                         |
|-----------|--------|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET       | /login | None                    | If you have entered valid credentials you will be logged in and redirected to the home page, <br/> otherwise you will stay on the page and be told that you have bad credentials. <br/> If you are already logged in you will be redirected to the homepage. |

#### Home page

The application has a home-page from which you can navigate to all other features of tha application.

| HTTP-verb | URL | Authorization           | Info                                                |
|-----------|-----|-------------------------|-----------------------------------------------------|
| GET       |     | All authenticated users | This will display the home-page of the application. |

#### Handling Categories

The application has a page for displaying exiting categories and one for adding/updating them.

| HTTP-verb | URL                           | Authorization           | Info                                                                       |
|-----------|-------------------------------|-------------------------|----------------------------------------------------------------------------|
| GET       | /categories                   | All authenticated users | This will displays all existing categories.                                |
| GET       | /addCategoryForm              | All authenticated users | This will displays a form where you can add a category                     |
| POST      | /saveCategory                 | All authenticated users | This will save a category witch has been added/updated                     |
| GET       | /categoriesUpdateForm?id={id} | All authenticated users | This will displays a form where you can update the category with id = {id} |
| GET       | /deleteCategory?id={id}       | All authenticated users | This will delete the category with id = {id}                               |

#### Handling Crimes

The application has a page for displaying exiting crimes and one for adding/updating them.

| HTTP-verb | URL                      | Authorization                       | Info                                                                    |
|-----------|--------------------------|-------------------------------------|-------------------------------------------------------------------------|
| GET       | /crimes                  | Authenticated users with role ADMIN | This will displays all existing crimes.                                 |
| GET       | /addCrimeForm            | Authenticated users with role ADMIN | This will displays a form where you can add a crime                     |
| POST      | /saveCrime               | Authenticated users with role ADMIN | This will save a crime witch has been added/updated                     |
| GET       | /crimeUpdateForm?id={id} | Authenticated users with role ADMIN | This will displays a form where you can update the crime with id = {id} |
| GET       | /deleteCrime?id={id}     | Authenticated users with role ADMIN | This will delete the crime with id = {id}                               |

#### Handling Criminals

The application has a page for displaying exiting criminals and one for adding/updating them.

| HTTP-verb | URL                         | Authorization                       | Info                                                                       |
|-----------|-----------------------------|-------------------------------------|----------------------------------------------------------------------------|
| GET       | /criminals                  | Authenticated users with role ADMIN | This will displays all existing criminals.                                 |
| GET       | /addCriminalForm            | Authenticated users with role ADMIN | This will displays a form where you can add a criminal                     |
| POST      | /saveCriminal               | Authenticated users with role ADMIN | This will save a criminal witch has been added/updated                     |
| GET       | /criminalUpdateForm?id={id} | Authenticated users with role ADMIN | This will displays a form where you can update the criminal with id = {id} |
| GET       | /deleteCriminal?id={id}     | Authenticated users with role ADMIN | This will delete the criminal with id = {id}                               |

#### Handling Victims

The application has a page for displaying exiting victims and one for adding/updating them.

| HTTP-verb | URL                       | Authorization                       | Info                                                                     |
|-----------|---------------------------|-------------------------------------|--------------------------------------------------------------------------|
| GET       | /victims                  | Authenticated users with role ADMIN | This will displays all existing victims.                                 |
| GET       | /addVictimForm            | Authenticated users with role ADMIN | This will displays a form where you can add a victim                     |
| POST      | /saveVictim               | Authenticated users with role ADMIN | This will save a victim witch has been added/updated                     |
| GET       | /victimUpdateForm?id={id} | Authenticated users with role ADMIN | This will displays a form where you can update the victim with id = {id} |
| GET       | /deleteVictim?id={id}     | Authenticated users with role ADMIN | This will delete the victim with id = {id}                               |

#### Handling Addresses

The application has a page for displaying exiting addresses and one for adding/updating them.

| HTTP-verb | URL                          | Authorization           | Info                                                                      |
|-----------|------------------------------|-------------------------|---------------------------------------------------------------------------|
| GET       | /addresses                   | All authenticated users | This will displays all existing addresses.                                |
| GET       | /addAddressesForm            | All authenticated users | This will displays a form where you can add an address                    |
| POST      | /saveAddress                 | All authenticated users | This will save a address witch has been added/updated                     |
| GET       | /addressesUpdateForm?id={id} | All authenticated users | This will displays a form where you can update the address with id = {id} |
| GET       | /deleteAddress?id={id}       | All authenticated users | This will delete the address with id = {id}                               |

#### Handling Users

The application has a page for displaying exiting users. Users can (at the moment) not be modified from the application.

| HTTP-verb | URL                       | Authorization                       | Info                                                                     |
|-----------|---------------------------|-------------------------------------|--------------------------------------------------------------------------|
| GET       | /users                    | Authenticated users with role ADMIN | This will displays all existing users.                                   |

