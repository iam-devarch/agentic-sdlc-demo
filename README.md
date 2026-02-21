# Agentic SDLC Demo

A **Spring Boot REST API microservice** built using the **Agentic SDLC** methodology — where AI agents collaborate through defined phases (Planning → Design → Coding → Testing → DevOps) to develop production-quality software.

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
│   ├── main/java/com/example/api/
│   └── test/java/com/example/api/
├── pom.xml
├── AGENTIC-SDLC-MANUAL.md  # Full SDLC process guide
└── README.md
```

## Agentic SDLC

This project uses AI agents organized into phases. See [AGENTIC-SDLC-MANUAL.md](AGENTIC-SDLC-MANUAL.md) for the full process documentation.

| Phase    | Agent            | Purpose                                    |
|----------|------------------|--------------------------------------------|
| Plan     | Planning Agent   | Create epics, break into user stories      |
| Design   | Design Agent     | API contracts, OpenAPI specifications      |
| Code     | Coding Agent     | Implement controllers, services, DTOs      |
| Test     | Testing Agent    | Generate JUnit tests, run test suite       |
| DevOps   | DevOps Agent     | Docker, CI/CD, mocks, documentation        |

The **Orchestrator Agent** coordinates all phases in sequence.

## Workflows

Use Antigravity workflows to invoke the agentic SDLC:

- `/agentic-sdlc` — Full lifecycle for a feature
- `/plan-feature` — Planning phase only
- `/design-feature` — Design phase only
- `/code-feature` — Coding phase only
- `/test-feature` — Testing phase only
- `/devops-setup` — DevOps phase only
