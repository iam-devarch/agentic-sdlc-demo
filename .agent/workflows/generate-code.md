---
description: Generate Spring Boot API interfaces and DTOs from a validated OpenAPI specification
---

# Generate Code Workflow

Execute the **Code Generation Phase** of the Agentic SDLC.

## Prerequisites
- OpenAPI spec must exist in `src/main/resources/api/{feature}-api.yaml`
- Spec should have passed validation (run `/validate-spec` first)

## Steps

1. **Read the Code Generation Agent instructions**
   Read `.agent/skills/02b-codegen-agent/SKILL.md` to understand the generation process.

2. **Read the OpenAPI specification**
   Review `src/main/resources/api/{feature}-api.yaml` to understand what will be generated.

3. **Execute CODEGEN_001 — Configure Maven Plugin**
   Update `pom.xml` to add/configure `openapi-generator-maven-plugin` for the feature.
   Add runtime dependencies if not present (`swagger-annotations`).

4. **Execute CODEGEN_002 — Generate Code**
   // turbo
   Run `mvn generate-sources` to produce API interfaces and DTOs.

5. **Execute CODEGEN_003 — Compile Project**
   // turbo
   Run `mvn compile` to verify the generated code compiles successfully.

6. **Verify**
   - Generated API interface exists in `target/generated-sources/`
   - Generated model classes exist in `target/generated-sources/`
   - `mvn compile` succeeds with zero errors
