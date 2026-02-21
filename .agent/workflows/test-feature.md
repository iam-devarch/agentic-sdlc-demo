---
description: Testing phase — generate JUnit 5 tests and run the test suite
---

# Test Feature Workflow

Execute the **Testing Phase** of the Agentic SDLC.

## Prerequisites
- Source code must compile (`mvn compile` succeeds)
- Controller, Service, and Mapper classes must exist

## Steps

1. **Read the Testing Agent instructions**
   Read `.agent/skills/04-testing-agent/SKILL.md` to understand testing standards.

2. **Read the source code**
   Review the Controller, Service, and Mapper implementations to understand what to test.

3. **Execute TESTING_001 — Generate Controller Tests**
   Create `src/test/java/com/example/api/controller/{Feature}ControllerTest.java` with MockMvc tests.

4. **Execute TESTING_002 — Generate Service Tests**
   Create `src/test/java/com/example/api/service/{Feature}ServiceTest.java` with Mockito tests.

5. **Execute TESTING_003 — Generate Mapper Tests**
   Create `src/test/java/com/example/api/mapper/{Feature}MapperTest.java` with mapping tests.

6. **Execute TESTING_004 — Run Test Suite**
   // turbo
   Run `mvn test` in project root and fix any test failures.

7. **Verify**
   - All test files exist in the correct packages
   - `mvn test` passes with zero failures
