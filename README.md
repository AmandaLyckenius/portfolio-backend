# Portfolio Backend (Spring Boot + MongoDB)

This is the backend API for my personal developer portfolio.
It provides:
- dynamic project data consumed by the frontend
- a contact endpoint that sends email messages directly to my inbox
- clean architecture with DTOs, services, and global exception handling
- professional logging and simple validation

The API is built with Spring Boot 3, runs on Java 21, and uses MongoDB as the database.

---

## Tech Stack
- Java 21
- Spring Boot 3 (Web, Validation, Mail)
- MongoDB
- Gradle
- Deployed on Render

---

## Endpoints

### GET `/api/projects`
Returns a list of projects.

Example:
```json
[
   {
      "title": "Vision Capsule",
      "summary": "Time-capsule web app that schedules messages for future delivery.",
      "slug": "vision-capsule",
      "githubUrl": "https://github.com/AmandaLyckenius/vision-capsule",
      "liveUrl": "https://visioncapsule.vercel.com",
      "tech": ["React", "TypeScript", "Spring Boot", "MongoDB"],
      "imageUrl": "/public/vision-capsule.png"
   },
   {
      "title": "Weather App",
      "summary": "A microservice-based application providing weather data with user authentication.",
      "slug": "weather-app",
      "githubUrl": "https://github.com/AmandaLyckenius/weather_app_auth",
      "liveUrl": "https://weatherapp.render.com",
      "tech": ["React", "TypeScript", "Spring Boot", "PostgreSQL"],
      "imageUrl": "/public/weather-app.png"
   }
]
```
### GET `/api/projects/{slug}`
Returns a single project.

Example:
```json
  {
    "title": "Vision Capsule",
    "summary": "Time-capsule web app that schedules messages for future delivery.",
    "slug": "vision-capsule",
    "githubUrl": "https://github.com/AmandaLyckenius/vision-capsule",
    "liveUrl": "https://visioncapsule.app",
    "tech": ["React", "TypeScript", "Spring Boot", "MongoDB"],
    "imageUrl": "/public/vision-capsule.png"
  }
```


### POST `/api/contact`
Sends a message from the frontend contact form to my inbox.
Uses Spring Boot Mail with a secured app password.

Example:
```json
  {
    "name": "Amanda",
    "email": "email@example.com",
    "message": "Hi! I'm interested in working with you"
  }
```

---

## Error Handling & Logging

The backend uses a GlobalControllerAdvice to centralize error handling:
- ProjectNotFoundException → 404
- SlugAlreadyExistsException → 409
- MailException → 503
- all errors logged with appropriate levels (INFO/WARN/ERROR)

Success events (e.g., new project, mail sent) are logged at the service layer.

--- 

## Installation & Running Locally
1. Clone the repository
   ```bash
   git clone <repo-url>
   cd portfolio_backend
   ```

2. Configure environment variables
Create an `application.properties` file (or use environment variables) with the following:
```properties
#MongoDB connection string
spring.data.mongodb.uri=<your mongo uri>

# Contact email settings
portfolio.contact.to=<recipient email>
portfolio.contact.from=<sender email>

# Gmail SMTP
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<your gmail address>
spring.mail.password=<your Google app password>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

3. Run the application
```bash
./gradlew bootRun
```

4. API will be available at
```
   http://localhost:8080/api
```

---

## Docker Support

The project is fully containerized using Docker.

### Build the Docker Image

```bash 
docker build -t portfolio-backend .
```

### Run the Container Locally

You can run the backend with:
```bash
docker run -p 8080:8080 \
  -e MONGODB_URI="<your-mongodb-uri>" \
  -e MAIL_USERNAME="<your-email>" \
  -e MAIL_PASSWORD="<your-google-app-password>" \
  -e CONTACT_TO_EMAIL="<recipient-email>" \
  -e CONTACT_FROM_EMAIL="<sender-email>" \
  -e MAIL_PORT="587" \
  portfolio-backend
```

### This exposes the API at:

```bash
http://localhost:8080/api
```

### Environment Variables Required

Inside Docker (and production), you must provide these:

```properties
MONGODB_URI=<Connection string for MongoDB>
MAIL_USERNAME=<Gmail address used for sending contact form emails>
MAIL_PASSWORD=<Gmail app password (not your real password!)>
CONTACT_TO_EMAIL=<Where incoming contact messages should be sent>
CONTACT_FROM_EMAIL=<The “from” address used in sent emails>
MAIL_PORT=587
```

---

## Related Repositories

Frontend (React + Vite):
https://github.com/AmandaLyckenius/portfolio-frontend
