# ShortURL - URL Shortener Service

## How to Run

### Prerequisites
- Java 22
- Maven

### Build and Start
```bash
mvn clean install
mvn spring-boot:run -Dspring-boot.run.arguments="--shorturl.concurrent.limit=10"
