# Agent Roles & Responsibilities

This document provides an overview of all AI agents used in the Agentic SDLC framework.

## Agent Registry

| Agent | Skill Folder | Phase | Description |
|-------|-------------|-------|-------------|
| **Orchestrator** | `00-orchestrator-agent` | All | Master coordinator — sequences phases, verifies outputs, handles failures |
| **Planning** | `01-planning-agent` | Planning | Creates Epics and User Stories from feature requests |
| **Design** | `02-design-agent` | Design | Designs REST API contracts and OpenAPI 3.0 specs |
| **Spec Validation** | `02a-spec-validation-agent` | Spec Validation | Validates OpenAPI specs for correctness, compliance, and compatibility |
| **Code Generation** | `02b-codegen-agent` | Code Generation | Generates API interfaces and DTOs from OpenAPI specs |
| **Coding** | `03-coding-agent` | Coding | Implements generated interfaces (Controller, Service, Mapper) |
| **Testing** | `04-testing-agent` | Testing | Generates JUnit 5 + contract tests and runs the test suite |
| **DevOps** | `05-devops-agent` | DevOps | Docker, CI/CD, WireMock stubs, documentation |

## Phase Dependencies

```
Planning ──▶ Design ──▶ Spec Validation ──▶ Code Generation ──▶ Coding ──▶ Testing ──▶ DevOps
   │            │             │                    │               │          │           │
   ▼            ▼             ▼                    ▼               ▼          ▼           ▼
 Epic.md     Design.md    Validation           Generated       Impl.java  *Test.java   Docker
 Stories     OpenAPI       Report             Interfaces       Service    Contracts    CI/CD
                              │                                              │
                              └──── Fail? ◀────────────────────── Fail? ────┘
                              Route back to Design              Route back to Coding
```

## How Agents Are Invoked

Agents are Antigravity **skills** located in `.agent/skills/`. They can be invoked:

1. **Via the Orchestrator**: Use the `/agentic-sdlc` workflow for full lifecycle
2. **Individually**: Use phase-specific workflows (`/plan-feature`, `/design-feature`, `/validate-spec`, `/generate-code`, `/code-feature`, `/test-feature`, `/devops-setup`)
3. **Directly**: Reference a skill by reading its `SKILL.md` instructions

## Skill Reference

See [SKILLS.md](SKILLS.md) for the complete skill registry with IDs, descriptions, and verification criteria.
