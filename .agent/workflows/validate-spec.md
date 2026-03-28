---
description: Validate an OpenAPI specification against schema rules, enterprise standards, and backwards compatibility
---

# Validate Spec Workflow

Execute the **Spec Validation Phase** of the Agentic SDLC.

## Prerequisites
- OpenAPI spec must exist in `src/main/resources/api/{feature}-api.yaml`
- API design document should exist in `docs/design/DESIGN-{FEATURE}-API.md`

## Steps

1. **Read the Spec Validation Agent instructions**
   Read `.agent/skills/02a-spec-validation-agent/SKILL.md` to understand the validation process.

2. **Identify the spec to validate**
   Locate the OpenAPI YAML file in `src/main/resources/api/`. If the user hasn't specified which feature, list available specs and ask.

3. **Execute SPECVAL_001 — Validate OpenAPI Schema**
   Validate the spec for structural correctness:
   - Valid OpenAPI 3.0/3.1 format
   - All references resolve
   - All operations have operationId and responses
   // turbo

4. **Execute SPECVAL_002 — Lint Against Enterprise Standards**
   Check naming conventions, REST conventions, error handling format, and pagination support.

5. **Execute SPECVAL_003 — Check Backwards Compatibility**
   If a previous version of the spec exists, check for breaking changes.
   If this is the first version, note it and skip.

6. **Report Results**
   Produce a validation summary with:
   - PASS/FAIL status for each check
   - List of errors (blocking)
   - List of warnings (informational)
   - Recommendation: proceed to coding or fix issues first

7. **Verify**
   - Zero errors across all checks
   - If errors found, report them and do NOT proceed to code generation
