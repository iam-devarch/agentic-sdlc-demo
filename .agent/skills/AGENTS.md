# Agent Roles & Responsibilities

This document provides an overview of all AI agents used in the Agentic SDLC framework.

## Agent Registry

| Agent | Skill Folder | Phase | Description |
|-------|-------------|-------|-------------|
| **Orchestrator** | `00-orchestrator-agent` | All | Master coordinator — sequences phases, verifies outputs |
| **Planning** | `01-planning-agent` | Planning | Creates Epics and User Stories from feature requests |
| **Design** | `02-design-agent` | Design | Designs REST API contracts and OpenAPI 3.0 specs |
| **Coding** | `03-coding-agent` | Coding | Implements Spring Boot code (Controller, Service, DTO, Mapper) |
| **Testing** | `04-testing-agent` | Testing | Generates JUnit 5 tests and runs the test suite |
| **DevOps** | `05-devops-agent` | DevOps | Docker, CI/CD, WireMock stubs, documentation |

## Phase Dependencies

```
Planning ──▶ Design ──▶ Coding ──▶ Testing ──▶ DevOps
   │             │          │          │           │
   ▼             ▼          ▼          ▼           ▼
 Epic.md     Design.md   *.java    *Test.java   Docker
 Stories     OpenAPI      Code      Reports     CI/CD
```

## How Agents Are Invoked

Agents are Antigravity **skills** located in `.agent/skills/`. They can be invoked:

1. **Via the Orchestrator**: Use the `/agentic-sdlc` workflow for full lifecycle
2. **Individually**: Use phase-specific workflows (`/plan-feature`, `/design-feature`, etc.)
3. **Directly**: Reference a skill by reading its `SKILL.md` instructions

## Skill Reference

See [SKILLS.md](SKILLS.md) for the complete skill registry with IDs, descriptions, and verification criteria.
