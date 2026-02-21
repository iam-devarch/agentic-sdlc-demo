---
description: Coding phase — implement Spring Boot REST API from design documents
---

# Code Feature Workflow

Execute the **Coding Phase** of the Agentic SDLC.

## Prerequisites
- API design document must exist in `docs/design/DESIGN-{FEATURE}-API.md`
- OpenAPI spec must exist in `src/main/resources/api/{feature}-api.yaml`

## Steps

1. **Read the Coding Agent instructions**
   Read `.agent/skills/03-coding-agent/SKILL.md` to understand coding standards.

2. **Read the design documents**
   Read both the API design document and OpenAPI spec to understand what to implement.

3. **Execute CODING_003 — Implement DTOs, Models, and Supporting Code**
   Create files in:
   - `src/main/java/com/example/api/dto/` — Request/Response DTOs with validation
   - `src/main/java/com/example/api/model/` — Domain model classes
   - `src/main/java/com/example/api/mapper/` — MapStruct mapper interfaces
   - `src/main/java/com/example/api/exception/` — Custom exceptions and `@ControllerAdvice`

4. **Execute CODING_002 — Implement Service Layer**
   Create `src/main/java/com/example/api/service/{Feature}Service.java` with business logic.

5. **Execute CODING_001 — Implement REST Controller**
   Create `src/main/java/com/example/api/controller/{Feature}Controller.java` with endpoints.

6. **Execute CODING_004 — Compile Project**
   // turbo
   Run `mvn compile` in project root and fix any errors.

7. **Verify**
   - All source files exist in the correct packages
   - `mvn compile` succeeds with zero errors
