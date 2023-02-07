# Olimpiadas Universitarias

## Final project for _Software Engineering 2022-2_

---

### Index

- [Description](#description)
- [Technology](#technology)
- [Team members](#team-members)
- [How to try it](#how-to-try-it)

---

## Description

The system offers different services for participants of a fictitious University Olympics. The users consist of competitors, judges, trainers, and the administrator. The following describes the services provided for each user:

- **Admin**:
  - Add/update/remove Olympic disciplines.
  - Add/update/remove Olympic judges.
  - Review the competitors' information.
  - Review the trainers' information.

| <img src="/img/olimpiadas-admin.gif" width="50%"> |
| :-----------------------------------------------: |
|                 _Admin services_                  |

- **Trainer**:
  - Register herself in the system.
  - Add/update/remove her competitors.
  - Review the score for each of her competitors.

| <img src="/img/olimpiadas-entrenador.gif" width="50%"> |
| :----------------------------------------------------: |
|                   _Trainer services_                   |

- **Competitor**:
  - Review the score that each judge assigned her.
  - Review her position in a competition and her average score.

| <img src="/img/olimpiadas-competidor.gif" width="50%"> |
| :----------------------------------------------------: |
|               **Competitor** services\*                |

- **Judge**:
  - Review the score that each judge assigned her.
  - Review her position in a competition and her average score.

| <img src="/img/olimpiadas-juez.gif" width="50%"> |
| :----------------------------------------------: |
|               **Judge** services\*               |

**[Back to index](#index)**

---

## Technology

- Java
- Spring
- MariaDB / MySQL
- Apache Tomcat
- Maven
- Lombok
- Thymeleaf
- HTML/CSS

**[Back to index](#index)**

---

## Team members

- [Saúl González](https://github.com/Vicroni)
- [Heidi Gómez](https://github.com/BethGomez44)
- [Ricardo Luévano](https://github.com/BethGomez44)
- Erick Martinez
- [Karina Prado](https://github.com/xKary)

The team's name is S.H.R.E.K. because each letter corresponds to the first letter of each member's name.

**[Back to index](#index)**

---

## How to try it

- We included the database's schema and some entries; you can find those [here](https://github.com/Luevateros/olimpiadasUniversitarias/tree/master/olimpiadas/src/main/resources/db). First import the file [tabla.sql](https://github.com/Luevateros/olimpiadasUniversitarias/blob/master/olimpiadas/src/main/resources/db/tablas.sql) to your DBMS, then the file [DML(proyecto).sql](<https://github.com/Luevateros/olimpiadasUniversitarias/blob/master/olimpiadas/src/main/resources/db/DML(proyecto).sql>).

- Include your `username` and `password` in the file [application.properties](https://github.com/Luevateros/olimpiadasUniversitarias/blob/master/olimpiadas/src/main/resources/application.properties).

- Run [OlimpiadasApplication.java](https://github.com/Luevateros/olimpiadasUniversitarias/blob/master/olimpiadas/src/main/java/com/shrek/olimpiadas/OlimpiadasApplication.java) from your IDE.

- Visit http://localhost:8080/.
