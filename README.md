# Employee Test Portal (ETP)

A small full-stack **Spring Boot** application built specifically as a **practice/training ground for Java application testing**. It models a simple employee management system (login, employee records, leave requests, attendance) and comes with a matching test suite that demonstrates unit testing, TestNG, BDD with Cucumber, and UI automation with Selenium.

## Features (Application Under Test)

- **Authentication & Authorization** — Form-based login secured with Spring Security, BCrypt password hashing, and role-based access control (`ADMIN`, `EMPLOYEE`).
- **Employee Management** — Create, activate/deactivate, and view employee records.
- **Leave Management** — Submit, approve, and reject leave requests.
- **Attendance Management** — Mark and view attendance records.
- **Dashboard** — Landing page after login, tailored to the user's role.
- **REST API** — JSON endpoints under `/api/**` for employees, leaves, and attendance, alongside the server-rendered (Thymeleaf) web UI.
- **Sample data** — An admin and a sample employee account are seeded automatically on startup.

## Tech Stack

| Layer            | Technology                                      |
|-------------------|--------------------------------------------------|
| Language           | Java 26                                          |
| Framework          | Spring Boot 4.1.1 (Web, Security, Data JPA, Validation) |
| View layer         | Thymeleaf                                        |
| Database           | MySQL (via `mysql-connector-j`)                  |
| Build tool         | Maven                                            |
| Boilerplate        | Lombok                                           |
| Unit testing       | JUnit (Spring Boot Test)                         |
| Test framework     | TestNG                                           |
| BDD testing        | Cucumber (Cucumber-Java + Cucumber-TestNG)       |
| UI automation      | Selenium WebDriver                               |

## Project Structure

```
src/main/java/com/company/etp/
├── controller/          # MVC controllers (server-rendered pages)
├── restcontroller/       # REST API controllers
├── service/              # Business logic interfaces + implementations
├── repository/           # Spring Data JPA repositories
├── model/                # JPA entities (Employee, User, Attendance, LeaveRequest, ...)
├── dto/                  # Request/response DTOs
├── enums/                # Status enums (AttendanceStatus, LeaveStatus)
├── security/             # Spring Security config + UserDetailsService
├── exception/            # Custom exceptions + REST exception handling
└── config/               # App-wide config and data seeding

src/main/resources/
├── templates/            # Thymeleaf HTML views (+ shared fragments)
├── static/                # CSS and JS assets
└── application.properties

src/test/java/com/company/etp/
├── service/               # JUnit & TestNG unit tests for the service layer
├── testng/                # Additional TestNG test class
├── selenium/              # Selenium WebDriver UI tests (login, locators)
└── cucumber/
    ├── CucumberTestRunner.java   # Runs the .feature files via TestNG
    └── steps/                    # Step definitions (e.g., LoginSteps)

src/test/resources/features/
└── EmployeeLogin.feature   # Gherkin scenario for employee login

postman/
└── EmployeeTestPortal.postman_collection.json   # Postman collection for the REST API
```

## Prerequisites

- JDK 26 (or the JDK version configured in `pom.xml`)
- Maven 3.9+
- MySQL Server running locally
- A browser + matching WebDriver on `PATH` for Selenium tests (e.g., Chrome + ChromeDriver)

## Setup

1. **Create the database**

   ```sql
   CREATE DATABASE EmployeeTestPortalApplication;
   ```

2. **Configure the datasource**

   Update `src/main/resources/application.properties` with your MySQL credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/EmployeeTestPortalApplication
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

   > ⚠️ The checked-in `application.properties` contains a hard-coded username/password. Replace these with your own credentials (and avoid committing real secrets) before running the project.

3. **Build the project**

   ```bash
   mvn clean install
   ```

4. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

   The app starts on **http://localhost:8080**.

## Default Login Credentials

Seeded automatically by `DataInitializer` on first run:

| Role     | Username    | Password       |
|----------|-------------|----------------|
| Admin    | `admin`     | `Admin@123`    |
| Employee | `employee1` | `Employee@123` |

`employee1` is linked to a sample employee record (`EMP001` — Employee One) and can only mark attendance for that linked record.

## Running the Tests

Run the full test suite:

```bash
mvn test
```

The project demonstrates several testing approaches, which can also be run selectively:

- **Unit tests (JUnit)** — service-layer tests such as `EmployeeServiceImplTest`, `LeaveServiceImplTest`, `AttendanceServiceImplTest`.
- **TestNG tests** — `EmployeeServiceTestNG` and `EmployeeTestNGTest`.
- **BDD tests (Cucumber + TestNG)** — `CucumberTestRunner` executes the Gherkin scenarios in `src/test/resources/features/*.feature` (currently covering employee login) against step definitions in `cucumber/steps`.
- **UI automation (Selenium)** — `LoginTest` and `LocatorTest` drive a real browser against the running application to verify login flow and page locators. Make sure the app is running on `localhost:8080` and a compatible WebDriver is available before executing these.

## REST API

A ready-made Postman collection is included at:

```
postman/EmployeeTestPortal.postman_collection.json
```

Import it into Postman to explore and test the `/api/employees`, `/api/leaves`, and `/api/attendance` endpoints. Access is role-gated (see `SecurityConfig`):

- `GET` endpoints — accessible to `ADMIN` and `EMPLOYEE`.
- `POST /api/employees` — `ADMIN` only.
- `PUT`/`DELETE` on leaves and attendance — `ADMIN` only.
- `POST` on leaves and attendance (creation) — `ADMIN` and `EMPLOYEE`.

## Notes

- This project is intended as a **learning/training sandbox for testing techniques** (unit, TestNG, BDD/Cucumber, and Selenium UI automation) rather than a production-ready application.
- CSRF protection is disabled for `/api/**` to simplify REST client/testing usage; the browser-based UI still uses form login with CSRF enabled by default elsewhere.
