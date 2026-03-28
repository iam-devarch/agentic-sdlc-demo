# Agent Skill Registry

This document catalogs all skills available across the Agentic SDLC framework. The Orchestrator Agent uses this registry to understand and execute the capabilities of each phase-specific agent.

---

## PLANNING

| Skill ID  | Description                                        | Owning Agent | Verification / Command                              |
|-----------|----------------------------------------------------|--------------|------------------------------------------------------|
| PLAN_001  | Create an Epic from a high-level feature request   | Planning     | Check for `docs/planning/EPIC-{FEATURE}.md`          |
| PLAN_002  | Break down an Epic into detailed User Stories      | Planning     | Verify stories have acceptance criteria              |

## DESIGN

| Skill ID    | Description                                            | Owning Agent | Verification / Command                                  |
|-------------|--------------------------------------------------------|--------------|----------------------------------------------------------|
| DESIGN_001  | Design a REST API contract based on planning documents | Design       | Check for `docs/design/DESIGN-{FEATURE}-API.md`         |
| DESIGN_002  | Create an OpenAPI 3.0 specification from the API design| Design       | Check for `src/main/resources/api/{feature}-api.yaml`    |

## SPEC VALIDATION ⭐

| Skill ID     | Description                                                  | Owning Agent     | Verification / Command                              |
|--------------|--------------------------------------------------------------|------------------|------------------------------------------------------|
| SPECVAL_001  | Validate OpenAPI spec against OAS 3.0/3.1 schema            | Spec Validation  | Spectral lint exits with 0 errors                    |
| SPECVAL_002  | Lint API against enterprise design rules and standards       | Spec Validation  | All enterprise rules pass                            |
| SPECVAL_003  | Check backwards compatibility against previous spec version  | Spec Validation  | No breaking changes detected (or first version)      |

## CODE GENERATION ⭐

| Skill ID     | Description                                                     | Owning Agent    | Verification / Command                              |
|--------------|-----------------------------------------------------------------|-----------------|------------------------------------------------------|
| CODEGEN_001  | Configure openapi-generator-maven-plugin in pom.xml             | Code Generation | Plugin config exists in `pom.xml`                    |
| CODEGEN_002  | Generate API interfaces and DTOs from validated OpenAPI spec    | Code Generation | `mvn generate-sources` succeeds                      |
| CODEGEN_003  | Compile project to verify generated code                        | Code Generation | `mvn compile`                                        |

## CODING

| Skill ID    | Description                                                     | Owning Agent | Verification / Command                              |
|-------------|------------------------------------------------------------------|--------------|------------------------------------------------------|
| CODING_001  | Implement Controller that implements generated API interface     | Coding       | Controller `implements {Tag}Api`                     |
| CODING_002  | Implement Service layer logic for the feature                    | Coding       | File `*Service.java` exists                          |
| CODING_003  | Implement Mappers, domain models, and exception handling         | Coding       | Check for files in `mapper/`, `model/`, `exception/` |
| CODING_004  | Compile the entire project to ensure code validity               | Coding       | `mvn compile`                                        |

## TESTING

| Skill ID     | Description                                        | Owning Agent | Verification / Command                              |
|--------------|----------------------------------------------------|--------------|------------------------------------------------------|
| TESTING_001  | Generate JUnit tests for the Controller layer      | Testing      | File `*ControllerTest.java` exists                   |
| TESTING_002  | Generate JUnit tests for the Service layer         | Testing      | File `*ServiceTest.java` exists                      |
| TESTING_003  | Generate JUnit tests for the Mapper layer          | Testing      | File `*MapperTest.java` exists                       |
| TESTING_004  | Run the entire test suite to validate implementation| Testing     | `mvn test`                                           |
| TESTING_005  | Generate API contract tests (spec conformance)     | Testing      | File `*ContractTest.java` exists                     |

## DEVOPS

| Skill ID    | Description                                            | Owning Agent | Verification / Command                              |
|-------------|--------------------------------------------------------|--------------|------------------------------------------------------|
| DEVOPS_001  | Create or update WireMock stubs for backend services   | DevOps       | Check for new files in `mocks/`                      |
| DEVOPS_002  | Update project documentation (e.g., README)            | DevOps       | Check for changes in `README.md`                     |
| DEVOPS_003  | Verify CI/CD pipeline configuration is valid           | DevOps       | Check syntax of `.github/workflows/ci-cd.yml`        |
| DEVOPS_004  | Create Docker configuration files                      | DevOps       | Check for `Dockerfile`, `docker-compose.yml`         |
