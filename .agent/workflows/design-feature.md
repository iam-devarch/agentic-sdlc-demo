---
description: Design phase — create REST API contract and OpenAPI specification for a feature
---

# Design Feature Workflow

Execute the **Design Phase** of the Agentic SDLC.

## Prerequisites
- Epic and User Stories must exist in `docs/planning/EPIC-{FEATURE}.md`

## Steps

1. **Read the Design Agent instructions**
   Read `.agent/skills/02-design-agent/SKILL.md` to understand the design process.

2. **Read the planning documents**
   Read `docs/planning/EPIC-{FEATURE}.md` to understand the feature requirements.

3. **Execute DESIGN_001 — Design API Contract**
   Create `docs/design/DESIGN-{FEATURE}-API.md` with:
   - Endpoint definitions (method, path, parameters, request/response)
   - Data model schemas
   - Error handling strategy
   - Pagination approach

4. **Execute DESIGN_002 — Create OpenAPI Spec**
   Create `src/main/resources/api/{feature}-api.yaml` with:
   - Valid OpenAPI 3.0 YAML
   - All paths and schemas
   - Validation constraints

5. **Verify**
   - Confirm `docs/design/DESIGN-{FEATURE}-API.md` exists
   - Confirm `src/main/resources/api/{feature}-api.yaml` exists
   - Confirm the OpenAPI spec matches the design document
