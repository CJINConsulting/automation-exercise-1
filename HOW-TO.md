# How to Run and View Selenium Cucumber Tests

This project contains UI automation tests for [Selenium's demo web form](https://www.selenium.dev/selenium/web/web-form.html), using Java, Selenium, Cucumber, and JUnit 5.

---

## Running Tests Locally

Make sure you have **Java 21+** and **Maven 3.8+** installed.

### Run the full suite:

```bash
mvn clean test
```

This will:
- Download WebDriver (via WebDriverManager)
- Launch the tests in Chrome
- Generate reports in `target/`

---

## Viewing Reports

After a test run, open:

```bash
target/cucumber-report.html
```

This HTML file shows:
- Feature and scenario status
- Step-by-step results
- Execution summary

Other available reports:
- `cucumber-report.json` — raw result data
- `cucumber-report.xml` — for CI integration

---

## Running Specific Tests

Run tests by tag:

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

Enable verbose output:

```bash
mvn test -Dcucumber.execution.verbose=true
```

---

## Directory Structure

```
src
├── test
│   ├── java
│   │   └── steps/, hooks/, pages/, runner/
│   └── resources
│       └── features/, data/
└── pom.xml
```

---

## Project Expansion

Given more time, this project could expand to include:

## Compatibility testing
This project currently runs in Chrome. We can add multiple browsers for compatibility coverage.
We can also have parameterised browser selection in Maven profile/config, or as a test matrix in github flows.

### Cloud-based tests
Running the tests against an array of browsers and devices, and versions of each.

## Test Data Management
There are certain tests that may benefit from having a common dataset, like the text fields. 
A set of dropdown values could be extracted to a csv or txt file instead of hardcoding.
We could also generate random test data (faker) instead of hardcoding values.

## Granular Feature Files
Split large feature files into smaller domain-based or field-based sections.

text-input.feature, dropdown.feature, checkbox.feature, etc.

This would make it a bit cleaner, and easier to tag (@smoke, @regression) and isolate for CI runs.

## API Tests for the Form
Use RestAssured to send/validate requests to the form.
Could also pair with UI tests for E2E confidence.

## Screenshots on Failure
Update After steps to capture UI state on asserts and when steps fail.

Save and attach to CI artifacts or HTML report

## Parallel Execution
Speed up execution on larger suites

## Negative and Edge Case Testing
Go beyond the “happy path” scenarios for invalid inputs.
Some tests I added here may go into that pack - submitting empty forms, invalid characters, confirming fields are disabled etc.

## Accessibility Testing
Add accessibility assertions after page load or specific interactions. (e.g. axe-core)

## Github Pages
If the project was public, it may be worth including a GH page for the html report for scheduled action runs.

