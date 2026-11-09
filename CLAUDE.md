# CLAUDE.md — Agent Guardrails for Java Order Management & POS System

## Project Overview
This repository contains a modular Point-of-Sale (POS) and order fulfillment platform implemented in Java using object-oriented principles, state machine transitions, and financial aggregation logic.

---

## 🛠️ Build & Test Commands

Always verify changes by executing the test suite prior to committing:

```bash
# If using Maven:
mvn clean test

# If using Gradle:
./gradlew test

# Compile & run standalone CLI:
javac Lunchbox.java Lunch.java
java Lunch
```

> **Rule:** Never commit or push changes without all tests compiling and passing with 0 failures and 0 errors.

---

## 🧪 Test-Driven Development (TDD) with JUnit

- **Write Tests First:** Any new feature, bugfix, or state transition must be driven by corresponding unit tests using **JUnit 5** (`org.junit.jupiter.api.*`).
- **Comprehensive Coverage:**
  - Test order creation and edge cases (negative quantities, zero or negative prices, null names/items).
  - Test state machine mutations (transitions from pending to delivered, re-delivery guards).
  - Test financial calculations (subtotals, aggregate revenue, discount thresholds, floating-point precision).
  - Test capacity boundaries (max order array/list limits).
- **Assertion Standards:** Use specific, descriptive assertions (`assertEquals`, `assertThrows`, `assertTrue`) with failure explanation messages.

---

## ☕ Java Conventions & Coding Standards

1. **No Raw Types:**
   - Always parameterize collections (e.g., `List<Lunchbox> orders = new ArrayList<>()`, never `List orders`).
   - Use generics with proper type bounds where applicable.
2. **Explicit Exception Handling:**
   - Never suppress exceptions with empty `catch` blocks or bare `catch (Exception e)`.
   - Distinguish and handle specific exceptions explicitly (e.g., `IllegalArgumentException`, `IllegalStateException`, `NoSuchElementException`).
   - Throw domain-appropriate exceptions with informative error messages when input invariants are violated.
3. **Encapsulation & Immutability:**
   - Keep all fields `private`. Provide accessors and domain-specific mutator methods rather than arbitrary public setters.
   - Favor immutable value objects where state transitions are not required.
4. **Clean Code & Formatting:**
   - Follow standard Google Java Style guide: 4-space indentation, camelCase identifiers, PascalCase classes, UPPER_SNAKE_CASE constants.
   - Provide clear Javadoc documentation for public methods and domain boundaries.

---

## 🔄 State Machine & Transaction Safety

1. **Deterministic Order States:**
   - Guard order state transitions strictly (e.g., an order cannot transition backwards from `DELIVERED` to `PENDING` without an audited reversal).
   - Ensure state validation occurs before modifying underlying data.
2. **Thread Safety & Data Consistency:**
   - Ensure in-memory collections and counter indices remain synchronized and safe against race conditions.
   - In operations affecting multiple attributes (e.g., modifying item quantity and recalculating total revenue), treat the update as an atomic unit to prevent partial state corruption.
3. **Financial Calculation Accuracy:**
   - Validate numerical inputs before calculations.
   - Ensure monetary rounding and output formatting adhere to standard financial formatting (`$%,.2f`).
   - Prevent overflow and underflow on quantities and monetary sums.

---

## 📋 Pre-Commit Checklist

Before opening a pull request or pushing to `main`:
1. [ ] Build cleanly without warnings (`javac -Xlint:all` or `mvn compile`).
2. [ ] All JUnit tests pass (`mvn test` / `./gradlew test`).
3. [ ] No raw generic types or unhandled generic warnings.
4. [ ] State machine invariants are guarded against invalid transitions.
5. [ ] Code follows Java encapsulation conventions and meaningful exception propagation.
