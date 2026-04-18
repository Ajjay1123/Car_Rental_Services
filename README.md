## Local Setup

### Prerequisites
- Java 17+
- PostgreSQL running locally
- Create a database named `car_rental_db`

### Configuration
```bash
cp src/main/resources/application.properties.example \
   src/main/resources/application.properties
```
Then open `application.properties` and set:
- `DB_USERNAME` → your PostgreSQL username (default: `postgres`)
- `DB_PASSWORD` → your PostgreSQL password
- `DB_URL` → change DB name if different from `car_rental_db`

### Run
```bash
./mvnw spring-boot:run
```
