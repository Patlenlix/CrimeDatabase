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
Vi har även delat upp uppgifter för nästa vecka samt diskuterat hur vi vill lägga upp vårt arbete (tider/dagar) så vi jobba mer synkat. 

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
- Gör klart exceptions
- Release tag
- Börja med Thymeleaf

#### End of notes 2022-03-18

---

# Projektmöte 4 - 2022-03-25

Vi diskuterade release.yml och hur vi ska kunna testa om den fungerar. Fick felmeddelande under stadiet (Build and pushDocker image). Vi löste detta genom att uppdatera Dockerfile och nu fungerar release.yml. Vi lade även till inställningar för CRFS disabling för CRUD endpoints vilket fick det att fungera för allt förutom delete. Vi fixade problem vi hade med filer som försvann från vår Thymeleaf implementation och gick igenom implementationen av basic JMS (RabbitMQ)

### ATT GÖRA

Patrik:
- JMS
- Rapport

Helena:
- Tester
- Form based security ev.

Felix:

- Thymeleaf

#### End of notes 2022-03-25

---

# Projektmöte 5 - 2022-04-04

Vi kollade på form login och kom fram till en bra lösning. Felix presenterade även sitt jobb med thymeleaf och vi
planerade det fortsätta arbetet med detta. Felix har skapat olika thymeleaf templates som övriga ska göra reviews på.
Det fanns några problem med thymeleaf som vi ska försöka lösa i veckan (problem när man via hemsidan vill uppdatera
data). Vi kikade även lite snabbt på de tester Helena jobbat med. Allt fungerade bra tills vi la till form login, nu
behöver testerna omarbetas lite (tidigare unauthorized blir nu redirected). Vi kom fram till att vi ska lägga fokus på
de delar vi har igång och inte börja med något nytt denna veckan. Vi bestämde därför att inte vidareutveckla JMS vilket
var tanken från början. Om vi har tid över senare i veckan planerar vi att byta från H2 till MySQL.

### ATT GÖRA

Patrik:

- Slutföra rapport
- Uppdatera README.md
- Se till att vi får rapport för våra tester

Helena:

- Fixa problem med tester
- Kolla på thymeleaf (reviews)

Felix:

- Jobba vidare med thymeleaf och försöka komma på lösning till nuvarande problem.

#### End of notes 2022-04-04

---