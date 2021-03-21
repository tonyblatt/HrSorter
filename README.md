# HrSorter

Developer Note: Requirements indicate that the input date is supposed to be M/D/YYYY. This causes the day field to be "Day of the Year" instead of the usual "Day of the Month". I have preserved this requirement in the code and in the example files. To change the code to use "Day of the Month" instead of "Day of the Year", alter DATE_FORMAT in LineParser.java to take in "M/d/YYYY" instead of "M/D/YYYY". (The test files should also be updated, if you update LineParser.) Using the "Day of the Year" instead of "Day of the Month" also seems to render the month part of the input as extraneous because the "Day of the Year" tells the parser what month the date is in regardless of the actual input value. However, a month value is still necessary for the date to get parsed (though it is not used when determining what the Date of Birth will actually be).

## Step 1

### Build

From the root folder of the project, execute:
 mvn clean install

### Execution

From the root folder of the project, execute:
- java -jar target/HrSorter-0.0.1-SNAPSHOT.war step1 (Path to input file) (Sort Option)

### Notes
- There are multiple valid input files in the src/main/resources folder.
- Valid Sort Options are currently:
-- email (sorts by email descending followed by last name ascending)
-- birthdate (sorts by date of birth ascending)
-- name (sorts by last name descending)
- Lines with a missing or improperly formatted date are skipped.
- Lines which are missing an item are skipped.

## Step 2

### Build

From the root folder of the project, execute:
 mvn clean install

### Execution

From the root folder of the project, execute:
- java -jar target/HrSorter-0.0.1-SNAPSHOT.war step2

### Notes
- Input new lines using a POST call to localhost:8080/records with the line record as the RequestBody (lines must be input 1 at a time)
- Get lines from sorted by email (sorts by email descending followed by last name ascending) by making a GET call to localhost:8080/records/email
- Get lines from sorted by date of birth (sorts by date of birth ascending) by making a GET call to localhost:8080/records/birthdate
- Get lines from sorted by last name (sorts by last name descending) by making a GET call to localhost:8080/records/name
- Improperly formatted input lines are skipped.

