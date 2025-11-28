Smart Task Manager - Full Stack Project

Backend:
- Spring Boot 3
- JWT authentication
- Basic entities: User, Task
- Role-based access (Admin/Manager/Employee enum)
- Endpoints:
  - POST /api/auth/register
  - POST /api/auth/login
  - GET  /api/tasks/me
  - GET  /api/tasks/dashboard
  - POST /api/tasks
  - PATCH /api/tasks/{id}/status

Frontend:
- Angular 16
- Simple login, dashboard, and task list pages
- Auth guard and interceptor

Quick start:
1) Backend:
   - cd backend
   - mvn spring-boot:run

2) Frontend:
   - cd frontend
   - npm install
   - npm start

Adjust DB and mail settings in backend/src/main/resources/application.properties.
