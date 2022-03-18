# Projektmöte 1 - 2022-03-08

### ATT GÖRA

- DrawSQL ER diagram
- Skapa projektet med Spring Initializ
    - ALLA
- Skapa projekt strukturen
    - ALLA
- Skapa entiteter
    - Skapa kopplingarna
    - Repository
    - Service - CRUD
    - Controller - CRUD
- README instruktioner

### ATT ANVÄNDA

- Spring Boot
- GitHub
- Issue - Review

### HUR JOBBA

- Stora mål först
- Sedan mindre mål

### IDEER

- Amazon liknande - affär, nerskakad
- Bokaffär - alla typer av kopplingar, se böcker eller lägga till böcker
- Polisdatabas - brottslingar

---

## CrimeDatabase

### Entities

- User

- Crime
    - name
    - category
    - time
    - place
    - criminals
    - victims
- Category
    - name
- Criminal
    - name
    - address
    - date-of-birth
    - crime
- Victim
    - name
    - address
    - date-of-birth
    - crime
- Address
    - city
    - zip-code
    - street address

---

DEPLOYMENT:

- Docker?
- Wrapper?

---

### [Project Notes Week 10](https://docs.google.com/document/d/1sN9vkcyzUYCXPfoDVCRUyKBABQtA_xFz9SZt18YcXR4/edit?usp=sharing)

#### End of notes 2022-03-08

---

# Projektmöte 2 - 2022-03-14

Gått igenom kvarliggande issues från vecka 10. Delat upp uppgifter för dagen och veckan. Omprioriterat och diskuterat angående tester och security.

### ATT GÖRA
- Exceptions
- Dockerfile
- Logger
- Release tag & Deployment
- Add Update CRUD endpoint
- Change to MySQL
- Read up on Maven Wrapper

#### End of notes 2022-03-14

---

# Projektmöte 3 - 2022-03-18

Gått igenom vad vi gjort i veckan samt pratat om kvarliggande issues. Gått igenom security och kollar på lösning till custom exceptions ihop. 
Vi har även delat upp uppgifter för nästa veckan samt diskuterat hur vi vill lägga upp vårt arbete (tider/dagar) så vi jobba mer synkat. 

Vi kom fram till:
- Jobba mån, tors, fre "dagtid" (sitta ihop i skolan de flesta måndagar och fredagar)
- Övrig tid fördelar man som man vill. Kolla på video, läsa på osv. görs då man har tid. Cirka 1 dag(8h)/vecka utöver den tid vi lägger ihop.

### ATT GÖRA
Patrik:
- JMS
Helena:
- Lägg in Security
- Uppdatera README
- Börja med Tester
Felix:
- Gör klare exceptions
- Release tag
- Börja med Thymeleaf

#### End of notes 2022-03-14

---