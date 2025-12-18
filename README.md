# Portfolio Backend (Spring Boot + MongoDB)

A simple backend API that serves portfolio projects.

---

## Tech Stack
- Java 21
- Spring Boot 3 (Web)
- Currently using **dummy data** (MongoDB will be added later)

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
    "liveUrl": "https://visioncapsule.app",
    "tech": ["React", "TypeScript", "Spring Boot", "MongoDB"]
  }
] 
```
### GET `/api/projects/{slug}`
Returns a single project.

Example:
```json
[
  {
    "title": "Vision Capsule",
    "summary": "Time-capsule web app that schedules messages for future delivery.",
    "slug": "vision-capsule",
    "githubUrl": "https://github.com/AmandaLyckenius/vision-capsule",
    "liveUrl": "https://visioncapsule.app",
    "tech": ["React", "TypeScript", "Spring Boot", "MongoDB"]
  }
] 
```

