# Training Services System (Monorepo) — H2 dev profile

## Quick start
In this folder, open terminals and run in order:

1. Eureka (service registry)
```bash
mvn -pl service-registry spring-boot:run -DskipTests
```
2. Course Service (H2)
```bash
mvn -pl course-service spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev
```
3. Registration Service (H2)
```bash
mvn -pl registration-service spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev
```
4. Attendance Service (H2)
```bash
mvn -pl attendance-service spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev
```
5. Certification Service (H2)
```bash
mvn -pl certification-service spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev
```
(Optional) Gateway
```bash
mvn -pl gateway spring-boot:run -DskipTests
```

## Endpoints
- Course: `GET http://localhost:9001/courses`
- Registration: `GET http://localhost:9002/registrations`
- Attendance: `GET http://localhost:9003/attendance`
- Certification: `GET http://localhost:9004/certificates`

H2 consoles:
- Course: `http://localhost:9001/h2-console` (JDBC: `jdbc:h2:mem:course_db`, user: `sa`, password: empty)
- Registration: `http://localhost:9002/h2-console` (JDBC: `jdbc:h2:mem:registration_db`)
- Attendance: `http://localhost:9003/h2-console` (JDBC: `jdbc:h2:mem:attendance_db`)
- Certification: `http://localhost:9004/h2-console` (JDBC: `jdbc:h2:mem:certification_db`)
