---
description: Coding phase — implement Spring Boot REST API from design documents
---

# Code Feature Workflow

Execute the **Coding Phase** of the Agentic SDLC.

## Prerequisites
- API design document must exist in `docs/design/DESIGN-{FEATURE}-API.md`
- OpenAPI spec must exist in `src/main/resources/api/{feature}-api.yaml`
- OpenAPI spec must have passed validation (`/validate-spec`)
- Code generation must have been run (`/generate-code`) — API interfaces and DTOs exist in `target/generated-sources/`

## Steps

1. **Read the Coding Agent instructions**
   Read `.agent/skills/03-coding-agent/SKILL.md` to understand coding standards.

2. **Read the design documents and generated interfaces**
   Read the API design document, OpenAPI spec, and review the generated API interface in `target/generated-sources/`.

3. **Execute CODING_003 — Implement Domain Model, Mappers, and Exceptions**
   Create files in:
   - `src/main/java/com/devarch/agenticsdlc/model/` — Domain model classes (standard Java, no Lombok)
   - `src/main/java/com/devarch/agenticsdlc/mapper/` — MapStruct mapper interfaces (domain ↔ generated DTO)
   - `src/main/java/com/devarch/agenticsdlc/exception/` — Custom exceptions and `@ControllerAdvice`
   - **Do NOT create DTOs** — they are generated from the OpenAPI spec

4. **Execute CODING_002 — Implement Service Layer**
   Create `src/main/java/com/devarch/agenticsdlc/service/{Feature}Service.java` with business logic.

5. **Execute CODING_001 — Implement REST Controller**
   Create `src/main/java/com/devarch/agenticsdlc/controller/{Feature}Controller.java` that **implements the generated API interface**.

6. **Execute CODING_004 — Compile Project**
   // turbo
   Run `mvn compile` in project root and fix any errors.

7. **Verify**
   - Controller implements the generated API interface
   - All source files exist in the correct packages
   - No manually-created DTOs that duplicate generated ones
   - `mvn compile` succeeds with zero errors
