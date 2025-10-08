# Training Services System

A microservices-based training management system built with Spring Boot, Spring Cloud, and Docker.

## Architecture

The system consists of:
- **Service Registry (Eureka)**: Service discovery
- **API Gateway**: Central entry point with routing
- **Course Service**: Manages training courses
- **Registration Service**: Handles student registrations
- **Attendance Service**: Tracks attendance records
- **Certification Service**: Issues certificates
- **RabbitMQ**: Event-driven messaging
- **MySQL**: Database for each service

## Prerequisites

- Java 17
- Maven 3.6+
- Docker & Docker Compose

## Quick Start

### Docker Compose (Recommended)

1. Build and start all services:
```bash
mvn -DskipTests clean package
docker compose up -d --build
```

2. Wait for services to start (check logs):
```bash
docker compose logs -f
```

### Development Mode (Local)

For development with H2 databases:

1. Start Eureka:
```bash
mvn -pl service-registry spring-boot:run -DskipTests
```

2. Start services (in separate terminals):
```bash
# Course Service
mvn -pl course-service spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev

# Registration Service
mvn -pl registration-service spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev

# Attendance Service
mvn -pl attendance-service spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev

# Certification Service
mvn -pl certification-service spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev

# Gateway (optional)
mvn -pl gateway spring-boot:run -DskipTests
```

## Service Endpoints

### Direct Access (Development)
- **Course Service**: http://localhost:9001
- **Registration Service**: http://localhost:9002
- **Attendance Service**: http://localhost:9003
- **Certification Service**: http://localhost:9004

### Via API Gateway (Production)
- **Gateway**: http://localhost:19020
- **All services**: http://localhost:19020/{service-prefix}

## API Endpoints

### Course Service
- `GET /courses` - List all courses
- `POST /courses` - Create course
- `GET /courses/{id}` - Get course by ID
- `PUT /courses/{id}` - Update course
- `DELETE /courses/{id}` - Delete course

### Registration Service
- `GET /registrations` - List all registrations
- `POST /registrations` - Create registration (publishes RabbitMQ event)
- `GET /registrations/{id}` - Get registration by ID
- `PUT /registrations/{id}` - Update registration
- `DELETE /registrations/{id}` - Delete registration

### Attendance Service
- `GET /attendance` - List all attendance records
- `POST /attendance` - Create attendance record
- `GET /attendance/{id}` - Get attendance by ID
- `PUT /attendance/{id}` - Update attendance
- `DELETE /attendance/{id}` - Delete attendance

### Certification Service
- `GET /certificates` - List certificates (with optional filters)
- `POST /certificates` - Issue certificate
- `GET /certificates/{id}` - Get certificate by ID

## Monitoring & Health Checks

### Service Registry
- **Eureka Dashboard**: http://localhost:8761

### Health Endpoints
- **Gateway**: http://localhost:19020/actuator/health
- **Course Service**: http://localhost:9001/actuator/health
- **Registration Service**: http://localhost:9002/actuator/health
- **Attendance Service**: http://localhost:9003/actuator/health
- **Certification Service**: http://localhost:9004/actuator/health

### H2 Console (Development)
- **Course Service**: http://localhost:9001/h2-console
- **Registration Service**: http://localhost:9002/h2-console
- **Attendance Service**: http://localhost:9003/h2-console
- **Certification Service**: http://localhost:9004/h2-console

### RabbitMQ Management
- **Management UI**: http://localhost:15672 (guest/guest)

## Testing the System

### 1. Ping Endpoints
Test basic connectivity:
```bash
# Direct access
curl http://localhost:9001/courses
curl http://localhost:9002/registrations
curl http://localhost:9003/attendance
curl http://localhost:9004/certificates

# Via Gateway
curl http://localhost:19020/courses
curl http://localhost:19020/registrations
curl http://localhost:19020/attendance
curl http://localhost:19020/certificates
```

### 2. Create Course
```bash
curl -X POST http://localhost:19020/courses \
  -H "Content-Type: application/json" \
  -d '{"name":"Java Programming","instructor":"Dr. Smith","capacity":30}'
```

### 3. Register Student
```bash
curl -X POST http://localhost:19020/registrations \
  -H "Content-Type: application/json" \
  -d '{"courseId":1,"studentId":"STU001"}'
```

### 4. Check Event Processing
Check attendance service logs for RabbitMQ event consumption:
```bash
docker compose logs attendance-service
```

## Configuration

### Profiles
- **dev**: H2 in-memory database, seed data enabled
- **docker**: MySQL database, no seed data

### Environment Variables
- `SPRING_PROFILES_ACTIVE`: Active profile
- `SPRING_DATASOURCE_URL`: Database connection
- `EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE`: Eureka server URL
- `SPRING_RABBITMQ_HOST`: RabbitMQ host

## Troubleshooting

### Common Issues

1. **503 Service Unavailable**: Check if services are registered in Eureka
2. **500 Internal Server Error**: Check database connection and DDL settings
3. **Connection Refused**: Verify service dependencies and health checks

### Logs
```bash
# View all logs
docker compose logs

# View specific service logs
docker compose logs course-service
docker compose logs registration-service
```

### Service Status
```bash
# Check running containers
docker compose ps

# Check service health
curl http://localhost:19020/actuator/health
```
