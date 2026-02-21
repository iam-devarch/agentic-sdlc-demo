---
description: Run the full Agentic SDLC lifecycle for a feature (Plan → Design → Code → Test → DevOps)
---

# Agentic SDLC — Full Lifecycle Workflow

This workflow orchestrates all five SDLC phases for a complete feature implementation.

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

5. **CODING PHASE** — Execute skills CODING_001 through CODING_004
   Read `.agent/skills/03-coding-agent/SKILL.md` and follow its instructions.
   - Implement Controller, Service, DTOs, Mappers, Exception handling
   - **Verify**: Run `mvn compile` and confirm zero errors
// turbo

6. **TESTING PHASE** — Execute skills TESTING_001 through TESTING_004
   Read `.agent/skills/04-testing-agent/SKILL.md` and follow its instructions.
   - Generate tests for Controller, Service, and Mapper layers
   - **Verify**: Run `mvn test` and confirm all tests pass
// turbo

7. **DEVOPS PHASE** — Execute skills DEVOPS_001 through DEVOPS_004
   Read `.agent/skills/05-devops-agent/SKILL.md` and follow its instructions.
   - Create Docker files, CI/CD pipeline, WireMock stubs (if needed), update README
   - **Verify**: All DevOps artifacts exist and are syntactically valid

8. **Final Report**
   Summarize what was created in each phase and confirm all verification criteria passed.
