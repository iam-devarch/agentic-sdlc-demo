# Agentic SDLC Demo

A **Spring Boot REST API microservice** built using the **Agentic SDLC** methodology — where AI agents collaborate through defined phases (Planning → Design → Validate → Generate → Code → Test → DevOps) to develop production-quality software using **spec-driven development**.

See the [Final Walkthrough v1.0](docs/walkthrough-v1.0.md) for a detailed overview of what was built.

## Tech Stack

| Layer       | Technology                       |
|-------------|----------------------------------|
| Runtime     | Java 21                          |
| Framework   | Spring Boot 3.4.2                |
| Build       | Maven                            |
| Mapping     | MapStruct 1.6.3                  |
| Validation  | Jakarta Bean Validation          |
| Testing     | JUnit 5 + Mockito + MockMvc      |
| Code Gen    | OpenAPI Generator Maven Plugin   |

## Quick Start

```bash
# Build
mvn clean compile

# Run tests
mvn test

# Run application
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

## Project Structure

```
agentic-sdlc-demo/
├── .agent/                  # Antigravity Agent definitions
│   ├── skills/              # Agent skills (one per SDLC phase)
│   └── workflows/           # Orchestration workflows
├── docs/                    # Project documentation
│   ├── planning/            # Epics & user stories (Planning Agent output)
│   └── design/              # API designs & OpenAPI specs (Design Agent output)
├── src/
│   ├── main/java/com/devarch/agenticsdlc/
│   │   ├── api/             # Generated API interfaces & DTOs (from OpenAPI spec)
│   │   ├── controller/      # Controllers implementing generated interfaces
│   │   ├── service/         # Business logic
│   │   ├── model/           # Domain models
│   │   ├── mapper/          # MapStruct mappers
│   │   └── exception/       # Custom exceptions & handlers
│   ├── main/resources/api/  # OpenAPI specifications (source of truth)
│   └── test/java/com/devarch/agenticsdlc/
├── pom.xml
├── AGENTIC-SDLC-MANUAL.md  # Full SDLC process guide
└── README.md
```

## Agentic SDLC

This project uses AI agents organized into phases with **spec-driven development** — the OpenAPI specification is the single source of truth, and API interfaces/DTOs are generated from it. See [AGENTIC-SDLC-MANUAL.md](AGENTIC-SDLC-MANUAL.md) for the full process documentation.

| Phase           | Agent                | Purpose                                             |
|-----------------|----------------------|------------------------------------------------------|
| Plan            | Planning Agent       | Create epics, break into user stories                |
| Design          | Design Agent         | API contracts, OpenAPI specifications                |
| Validate ⭐     | Spec Validation Agent| Validate spec correctness, enterprise compliance     |
| Generate ⭐     | Code Generation Agent| Generate API interfaces & DTOs from OpenAPI spec     |
| Code            | Coding Agent         | Implement generated interfaces, services, mappers    |
| Test            | Testing Agent        | Unit tests, contract tests, run test suite           |
| DevOps          | DevOps Agent         | Docker, CI/CD, mocks, documentation                  |

The **Orchestrator Agent** coordinates all phases in sequence with feedback loops.

## Workflows

Use Antigravity workflows to invoke the agentic SDLC:

- `/agentic-sdlc` — Full lifecycle for a feature
- `/plan-feature` — Planning phase only
- `/design-feature` — Design phase only
- `/validate-spec` — Spec validation only
- `/generate-code` — Code generation only
- `/code-feature` — Coding phase only
- `/test-feature` — Testing phase only
- `/devops-setup` — DevOps phase only

## Feature: Wishlist

Allows users to manage a personal list of products they are interested in.

### API Endpoints
- `GET /api/v1/wishlist/{userId}` — Get or create user wishlist
- `POST /api/v1/wishlist/{userId}/add/{productId}` — Add product to wishlist
- `DELETE /api/v1/wishlist/{userId}/remove/{productId}` — Remove product from wishlist

### Implementation Details
- Uses MapStruct for DTO mapping.
- In-memory storage with `ConcurrentHashMap`.
- Automatic wishlist creation on first access/add.
- Duplicate prevention for products within a wishlist.
