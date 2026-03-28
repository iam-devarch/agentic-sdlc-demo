---
description: Testing phase — generate JUnit 5 tests and run the test suite
---

# Test Feature Workflow

Execute the **Testing Phase** of the Agentic SDLC.

## Prerequisites
- Source code must compile (`mvn compile` succeeds)
- Controller, Service, and Mapper classes must exist
- OpenAPI spec must exist in `src/main/resources/api/{feature}-api.yaml`

## Steps

1. **Read the Testing Agent instructions**
   Read `.agent/skills/04-testing-agent/SKILL.md` to understand testing standards.

2. **Read the source code**
   Review the Controller, Service, and Mapper implementations to understand what to test.

3. **Execute TESTING_001 — Generate Controller Tests**
   Create `src/test/java/com/devarch/agenticsdlc/controller/{Feature}ControllerTest.java` with MockMvc tests.

4. **Execute TESTING_002 — Generate Service Tests**
   Create `src/test/java/com/devarch/agenticsdlc/service/{Feature}ServiceTest.java` with Mockito tests.

5. **Execute TESTING_003 — Generate Mapper Tests**
   Create `src/test/java/com/devarch/agenticsdlc/mapper/{Feature}MapperTest.java` with mapping tests.

6. **Execute TESTING_005 — Generate Contract Tests**
   Create `src/test/java/com/devarch/agenticsdlc/contract/{Feature}ContractTest.java` with SpringBootTest integration tests that validate API responses match the OpenAPI spec.

7. **Execute TESTING_004 — Run Test Suite**
   // turbo
   Run `mvn test` in project root and fix any test failures.

8. **Verify**
   - All test files exist in the correct packages (controller, service, mapper, contract)
   - `mvn test` passes with zero failures
