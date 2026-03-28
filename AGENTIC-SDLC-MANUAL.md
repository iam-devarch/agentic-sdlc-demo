# Agentic SDLC Manual

This document describes the **Agentic Software Development Lifecycle (SDLC)** process used in this project. AI agents are orchestrated through well-defined phases to develop production-quality Spring Boot microservices using **spec-driven development**.

---

## Overview

The Agentic SDLC replaces traditional manual development with a **phase-driven, agent-orchestrated** approach. Each phase has a dedicated agent with specific skills, inputs, and outputs. The OpenAPI specification serves as the **single source of truth** — code is generated from the spec, not manually written.

```
Feature Request
     │
     ▼
┌─────────────┐     ┌──────────────┐     ┌────────────────┐     ┌────────────────┐
│  PLANNING   │────▶│    DESIGN    │────▶│ SPEC VALIDATION│────▶│CODE GENERATION │
│   Agent     │     │    Agent     │     │    Agent  ⭐    │     │   Agent  ⭐     │
└─────────────┘     └──────────────┘     └────────────────┘     └────────────────┘
                           ▲                    │                       │
                           └── Fail? ───────────┘                      │
                                                                       ▼
     ┌─────────────┐     ┌──────────────┐                      ┌──────────────┐
     │   DEVOPS    │◀────│   TESTING   │◀─────────────────────│    CODING    │
     │   Agent     │     │    Agent     │                      │    Agent     │
     └─────────────┘     └──────────────┘                      └──────────────┘
                                │                                      ▲
                                └────── Fail? ─────────────────────────┘
```

The **Orchestrator Agent** coordinates the entire flow with feedback loops.

---

## Phases

### 1. Planning Phase

**Agent**: `01-planning-agent`

| Skill ID  | Description                                        | Output                              |
|-----------|----------------------------------------------------|--------------------------------------|
| PLAN_001  | Create an Epic from a high-level feature request   | `docs/planning/EPIC-{FEATURE}.md`   |
| PLAN_002  | Break down an Epic into detailed User Stories      | User stories with acceptance criteria|

### 2. Design Phase

**Agent**: `02-design-agent`

| Skill ID    | Description                                          | Output                                          |
|-------------|------------------------------------------------------|--------------------------------------------------|
| DESIGN_001  | Design a REST API contract from planning documents   | `docs/design/DESIGN-{FEATURE}.md`               |
| DESIGN_002  | Create an OpenAPI 3.0 specification                  | `src/main/resources/api/{feature}-api.yaml`      |

### 3. Spec Validation Phase ⭐

**Agent**: `02a-spec-validation-agent`

| Skill ID     | Description                                                  | Output                              |
|--------------|--------------------------------------------------------------|--------------------------------------|
| SPECVAL_001  | Validate OpenAPI spec against OAS 3.0/3.1 schema            | Validation report (pass/fail)       |
| SPECVAL_002  | Lint API against enterprise design rules and standards       | Lint report with errors/warnings    |
| SPECVAL_003  | Check backwards compatibility against previous spec version  | Compatibility report                |

**Gate**: If validation fails, route back to **Design Phase** to fix the spec.

### 4. Code Generation Phase ⭐

**Agent**: `02b-codegen-agent`

| Skill ID     | Description                                                     | Output                                      |
|--------------|-----------------------------------------------------------------|----------------------------------------------|
| CODEGEN_001  | Configure openapi-generator-maven-plugin in pom.xml             | Updated `pom.xml` with plugin config        |
| CODEGEN_002  | Generate API interfaces and DTOs from validated OpenAPI spec    | `target/generated-sources/openapi/`         |
| CODEGEN_003  | Compile project to verify generated code                        | `mvn compile` passes                        |

### 5. Coding Phase

**Agent**: `03-coding-agent`

| Skill ID    | Description                                                     | Output                              |
|-------------|-----------------------------------------------------------------|--------------------------------------|
| CODING_001  | Implement Controller that implements generated API interface     | `*Controller.java`                   |
| CODING_002  | Implement Service layer logic                                    | `*Service.java`                      |
| CODING_003  | Implement Mappers, domain models, and exception handling         | Files in `mapper/`, `model/`, `exception/` |
| CODING_004  | Compile the project to ensure validity                           | `mvn compile` passes                 |

### 6. Testing Phase

**Agent**: `04-testing-agent`

| Skill ID     | Description                                    | Output                              |
|--------------|------------------------------------------------|--------------------------------------|
| TESTING_001  | Generate JUnit tests for Controller layer      | `*ControllerTest.java`              |
| TESTING_002  | Generate JUnit tests for Service layer         | `*ServiceTest.java`                 |
| TESTING_003  | Generate JUnit tests for Mapper layer          | `*MapperTest.java`                  |
| TESTING_004  | Run the entire test suite                      | `mvn test` passes                   |
| TESTING_005  | Generate API contract tests (spec conformance) | `*ContractTest.java`                |

**Gate**: If tests fail, route back to **Coding Phase** to fix implementation.

### 7. DevOps Phase

**Agent**: `05-devops-agent`

| Skill ID    | Description                                          | Output                                      |
|-------------|------------------------------------------------------|----------------------------------------------|
| DEVOPS_001  | Create WireMock stubs for backend services           | Files in `mocks/`                            |
| DEVOPS_002  | Update project documentation                         | Changes in `README.md`                       |
| DEVOPS_003  | Verify CI/CD pipeline configuration                  | `.github/workflows/ci-cd.yml`                |
| DEVOPS_004  | Create Docker configuration                          | `Dockerfile`, `docker-compose.yml`           |

---

## How to Use

### Full Lifecycle
Use the `/agentic-sdlc` workflow to run all phases for a feature:
```
/agentic-sdlc
Feature: [describe the feature]
```

### Individual Phases
Run specific phases independently:
```
/plan-feature      — Planning only
/design-feature    — Design only
/validate-spec     — Spec validation only
/generate-code     — Code generation only
/code-feature      — Coding only
/test-feature      — Testing only
/devops-setup      — DevOps only
```

---

## Verification

Each skill has a verification criterion:

| Phase           | Verification                                              |
|-----------------|-----------------------------------------------------------|
| Planning        | `docs/planning/EPIC-{FEATURE}.md` exists and has stories  |
| Design          | `docs/design/DESIGN-{FEATURE}.md` + OpenAPI spec exist    |
| Spec Validation | Spec passes schema validation and enterprise lint rules    |
| Code Generation | Generated interfaces compile (`mvn generate-sources`)      |
| Coding          | `mvn compile` succeeds                                     |
| Testing         | `mvn test` succeeds (including contract tests)             |
| DevOps          | Docker/CI-CD files exist and are syntactically valid        |
