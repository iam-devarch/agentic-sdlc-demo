---
description: DevOps phase — create Docker, CI/CD, mocks, and update documentation
---

# DevOps Setup Workflow

Execute the **DevOps Phase** of the Agentic SDLC.

## Prerequisites
- All tests must pass (`mvn test` succeeds)
- Source code is complete and compiles

## Steps

1. **Read the DevOps Agent instructions**
   Read `.agent/skills/05-devops-agent/SKILL.md` to understand DevOps standards.

2. **Execute DEVOPS_004 — Create Docker Configuration**
   Create:
   - `Dockerfile` (multi-stage build from source)
   - `Dockerfile.ci` (lightweight, pre-built JAR)
   - `docker-compose.yml` (local development)
   - `.dockerignore`

3. **Execute DEVOPS_003 — Create CI/CD Pipeline**
   Create `.github/workflows/ci-cd.yml` with build, test, and package stages.

4. **Execute DEVOPS_001 — Create WireMock Stubs** (if applicable)
   If the project has external service dependencies, create stubs in `mocks/`.

5. **Execute DEVOPS_002 — Update Documentation**
   Update `README.md` to reflect the current feature set, endpoints, and setup instructions.

6. **Verify**
   - `Dockerfile` exists and is syntactically valid
   - `.github/workflows/ci-cd.yml` exists with valid YAML
   - `README.md` is up to date
