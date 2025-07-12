# Helmes home assignment

Repo for Helmes home assignment.

## Stack

Frontend:

- Angular
- Node 22+

Backend:

- Java 21+
- Spring Boot 3
- PostgreSQL

## Database schema

Databse migrations are located in `/backend/src/main/resources/db/migration` folder
and are run on application start using Flyway.

### sector

| Column      | Type      | Constraints                     | Description                                     |
| ----------- | --------- | ------------------------------- | ----------------------------------------------- |
| id          | uuid      | PRIMARY KEY, NOT NULL           | Unique identifier for the sector                |
| name        | text      | NOT NULL                        | Name of the sector                              |
| created_at  | timestamp | NOT NULL                        | Record creation timestamp                       |
| modified_at | timestamp | NOT NULL                        | Record last modification timestamp              |
| parent_id   | uuid      | FOREIGN KEY, ON DELETE SET NULL | References sector.id for hierarchical structure |

### input

| Column         | Type      | Constraints             | Description                        |
| -------------- | --------- | ----------------------- | ---------------------------------- |
| id             | uuid      | PRIMARY KEY, NOT NULL   | Unique identifier for the input    |
| name           | text      | NOT NULL                | Name of the input                  |
| terms_accepted | boolean   | NOT NULL, DEFAULT false | Whether terms have been accepted   |
| created_at     | timestamp | NOT NULL                | Record creation timestamp          |
| modified_at    | timestamp | NOT NULL                | Record last modification timestamp |
| owner          | text      | NULLABLE                | Owner of the input (session id)    |

### rel_sector\_\_input

| Column    | Type | Constraints                        | Description          |
| --------- | ---- | ---------------------------------- | -------------------- |
| sector_id | uuid | PRIMARY KEY, NOT NULL, FOREIGN KEY | References sector.id |
| input_id  | uuid | PRIMARY KEY, NOT NULL, FOREIGN KEY | References input.id  |

## Development instructions

For setting up frontend development:

Angular CLI is a requirement, installation instructions are [here](https://angular.dev/tools/cli/setup-local).

After the CLI has been installed, you can run the following commands:

```bash
cd ./frontend
npm i
ng serve
```

For setting up backend development:

```bash
cd ./backend
docker compose -f ./src/main/resources/docker/postgres.yml up
./gradlew bootRun
```

Backend test run (requires docker for test containers):

```bash
cd ./backend
./gradlew integrationTest
```
