# HrSorter

## Step 1

### Build

From the root folder of the project, execute:
 mvn clean install

### Execution

From the root folder of the project, execute:
 java -jar target/HrSorter-0.0.1-SNAPSHOT.war step1 (Path to input file) (Sort Option)

### Notes
- There are multiple valid input files in the src/main/resources folder.
- Valid Sort Options are currently:
-- email (sorts by email descending followed by last name ascending)
-- birthdate (sorts by date of birth ascending)
-- name (sorts by last name descending)
- Lines with a missing or improperly formatted date are skipped.
- Lines which are missing an item are skipped.