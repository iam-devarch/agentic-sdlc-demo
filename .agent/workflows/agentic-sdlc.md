---
description: Run the full Agentic SDLC lifecycle for a feature (Plan → Design → Validate → Generate → Code → Test → DevOps)
---

# Agentic SDLC — Full Lifecycle Workflow

This workflow orchestrates all seven SDLC phases for a complete feature implementation using spec-driven development.

## Prerequisites
- Feature request description from the user
- Spring Boot project skeleton exists (`pom.xml`, `Application.java`)

## Steps

1. **Read the Orchestrator Agent instructions**
   Read `.agent/skills/00-orchestrator-agent/SKILL.md` to understand the full lifecycle flow.

2. **Read the Skill Registry**
   Read `.agent/skills/SKILLS.md` to understand all available skills and their verification criteria.

3. **PLANNING PHASE** — Execute skills PLAN_001 and PLAN_002
   Read `.agent/skills/01-planning-agent/SKILL.md` and follow its instructions.
   - Create `docs/planning/EPIC-{FEATURE}.md` with the epic and user stories
   - **Verify**: File exists and contains user stories with acceptance criteria

4. **DESIGN PHASE** — Execute skills DESIGN_001 and DESIGN_002
   Read `.agent/skills/02-design-agent/SKILL.md` and follow its instructions.
   - Create `docs/design/DESIGN-{FEATURE}-API.md` with API contract
   - Create `src/main/resources/api/{feature}-api.yaml` with OpenAPI spec
   - **Verify**: Both files exist and are consistent

5. **SPEC VALIDATION PHASE** — Execute skills SPECVAL_001 through SPECVAL_003
   Read `.agent/skills/02a-spec-validation-agent/SKILL.md` and follow its instructions.
   - Validate OpenAPI spec for schema correctness
   - Lint against enterprise API standards
   - Check backwards compatibility (if previous version exists)
   - **Verify**: Zero validation errors
   - **On failure**: Route back to step 4 (Design Phase) to fix the spec

6. **CODE GENERATION PHASE** — Execute skills CODEGEN_001 through CODEGEN_003
   Read `.agent/skills/02b-codegen-agent/SKILL.md` and follow its instructions.
   - Configure openapi-generator-maven-plugin in pom.xml
   - Generate API interfaces and DTOs from the validated spec
   - **Verify**: Run `mvn compile` and confirm generated code compiles
// turbo

7. **CODING PHASE** — Execute skills CODING_001 through CODING_004
   Read `.agent/skills/03-coding-agent/SKILL.md` and follow its instructions.
   - Implement Controller that implements the generated API interface
   - Implement Service layer, Mappers, domain models, exception handling
   - Do NOT manually create DTOs (they are generated from the spec)
   - **Verify**: Run `mvn compile` and confirm zero errors
// turbo

8. **TESTING PHASE** — Execute skills TESTING_001 through TESTING_005
   Read `.agent/skills/04-testing-agent/SKILL.md` and follow its instructions.
   - Generate tests for Controller, Service, and Mapper layers
   - Generate contract tests that validate API responses match the OpenAPI spec
   - **Verify**: Run `mvn test` and confirm all tests pass (including contract tests)
   - **On failure**: Route back to step 7 (Coding Phase) to fix implementation
// turbo

9. **DEVOPS PHASE** — Execute skills DEVOPS_001 through DEVOPS_004
   Read `.agent/skills/05-devops-agent/SKILL.md` and follow its instructions.
   - Create Docker files, CI/CD pipeline, WireMock stubs (if needed), update README
   - **Verify**: All DevOps artifacts exist and are syntactically valid

10. **Final Report**
    Summarize what was created in each phase and confirm all verification criteria passed.
