# Claims & Enrollment Automation

This project is a Spring Boot application for automating claims and enrollment processing, integrating both database and UI automation. It uses Selenium WebDriver for browser automation, connects to a SQL Server database, and supports BDD testing with Cucumber.

## Features
- **Spring Boot** backend for automation workflows
- **SQL Server** integration for claims and enrollment data
- **Selenium WebDriver** for UI automation (e.g., SauceDemo login)
- **Extent Reports** for test reporting
- **Cucumber BDD** for writing and running feature-based tests

## Prerequisites
- Java 17+
- Maven 3.6+
- SQL Server (with a `claims` table as per the schema)
- Chrome browser (for Selenium)
- Docker (optional, for running SQL Server locally)

## Setup
1. **Clone the repository:**
   ```bash
   git clone https://github.com/quatmohan/NewTest.git
   cd NewTest
   ```
2. **Configure the database:**
   Edit `src/main/resources/application.properties` with your SQL Server connection details.

3. **Build the project:**
   ```bash
   mvn clean install
   ```

## Running the Application
- **Process a claim or enrollment by ID:**
  ```bash
  mvn spring-boot:run -Dspring-boot.run.arguments="claim 1"
  # or
  mvn spring-boot:run -Dspring-boot.run.arguments="enrollment 2"
  ```

- **Run all tests (including DB and UI logic):**
  ```bash
  mvn test
  ```

## BDD (Cucumber) Usage
- Feature files and step definitions (if present) are in `src/test/resources/features/` and `src/test/java/com/automation/stepdefs/`.
- To run BDD tests:
  ```bash
  mvn test -Dtest=SauceDemoCucumberTest
  ```

## Reporting
- Test execution generates an `extent-report.html` file in the project root.

## Example DB Schema (claims table)
| claim_id | member_id | claim_number | claim_date | claim_status | claim_amount | provider_id | service_type | created_date | updated_date |
|----------|-----------|--------------|------------|--------------|-------------|-------------|-------------|--------------|--------------|
| ...      | ...       | ...          | ...        | ...          | ...         | ...         | ...         | ...          | ...          |

## UI Automation Example
- The project includes a Selenium-based login flow for [SauceDemo](https://www.saucedemo.com/).

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](LICENSE) 