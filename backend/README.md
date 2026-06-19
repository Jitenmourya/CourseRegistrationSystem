Spring Boot backend for CourseRegistrationSystem

Run locally:

1. Ensure PostgreSQL is running and `course_db` exists. Apply `database.sql` if needed.
2. From `backend/` run:

```bash
mvn spring-boot:run
```

API endpoints:
- `GET /api/students` — list students
- `POST /api/students` — create student (JSON {name,email,department})
- `DELETE /api/students/{id}` — delete
- `GET /api/courses` — list courses
- `POST /api/courses` — create course
- `DELETE /api/courses/{id}` — delete
- `POST /api/registrations` — register course (JSON {student_id,course_id})

Cross-origin: If you serve frontend from static server on port 8000, enable CORS or serve frontend from same host. For quick test, open pages via `http://localhost:8080/pages/...` by copying `pages` into `backend/src/main/resources/static`.
