package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

import application.constants.HrSorterConstants;
import application.model.Person;
import application.parser.LineParser;
import application.parser.LineParser.InvalidNumberOfItemsInLineException;
import application.sorter.PersonSorter;
import application.sorter.SortOptions;

@SpringBootApplication
public class HrSorterApplication {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			printUsage();
		} else if ("step1".equals(args[0])) {
			if (args.length != 3) {
				printUsage();
				return;
			}
			step1(args);
		} else if ("step2".equals(args[0])) {
			SpringApplication.run(HrSorterApplication.class, args);
		} else {
			printUsage();
		}
	}

	/**
	 * Prints the correct usage of this application.
	 */
	private static void printUsage() {
		System.out.println("Improper usage. Correct usages:");
		System.out.println("java -jar target/HrSorter-0.0.1-SNAPSHOT.war step1 <Path to input file> <Sort Option>");
		System.out.println("java -jar target/HrSorter-0.0.1-SNAPSHOT.war step2");
	}

	/**
	 * Solution for step 1.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void step1(String[] args) throws IOException {
		processFile(args[1], args[2]);
	}

	/**
	 * Makes sure the file and sort option both exist. Reads in the file. Calls
	 * another method to perform the sort.
	 * 
	 * @param fileLocation The absolute or relative path to the file to sort.
	 * @param sortOption   The type of sort to perform.
	 * @throws IOException The file was not found.
	 */
	public static void processFile(String fileLocation, String sortOption) throws IOException {
		Path filePath = Paths.get(fileLocation);
		boolean fileExists = Files.exists(filePath);
		Optional<SortOptions> option = SortOptions.getOptionFromCode(sortOption);
		boolean optionExists = option.isPresent();

		if (!fileExists || !optionExists) {
			if (!fileExists) {
				System.out.println("Error: File " + fileLocation + " was not found.");
			}
			if (!optionExists) {
				System.out.println(buildOptionDoesNotExistOutputString(sortOption));
			}
			return;
		}

		List<String> fileLines = Files.readAllLines(filePath);
		List<Person> sortedPeople = sortFile(fileLines, option.get());
		System.out.println(new Gson().toJson(sortedPeople));
	}

	/**
	 * Parses the file into people. Sorts the people.
	 * 
	 * @param fileLines The lines of the file to sort.
	 * @param option    The type of sort to do.
	 * @return The sorted people.
	 */
	public static List<Person> sortFile(List<String> fileLines, SortOptions option) {
		List<Person> parsed = new ArrayList<>(fileLines.size());
		for (int i = 0; i < fileLines.size(); i++) {
			String line = fileLines.get(i);
			try {
				Person person = LineParser.parseLine(i, line, LineParser.findDelimiter(line));
				parsed.add(person);
			} catch (IllegalArgumentException e) {
				System.out.println(String.format(HrSorterConstants.INVALID_DATE_FORMAT, line));
			} catch (InvalidNumberOfItemsInLineException e) {
				System.out.println(String.format(HrSorterConstants.INVALID_NUMBER_OF_ITEMS, line));
			}
		}
		PersonSorter.sortPeople(parsed, option);
		return parsed;
	}

	/**
	 * Utility method for building the output string for when the sort option does
	 * not exist.
	 * 
	 * @param inputSortOption The sort type that does not exist.
	 * @return The string explaining to the user that the sort option does not
	 *         exist.
	 */
	public static String buildOptionDoesNotExistOutputString(String inputSortOption) {
		StringBuilder optionDoesNotExistMessage = new StringBuilder("Provided Option: ");
		optionDoesNotExistMessage.append(inputSortOption);
		optionDoesNotExistMessage.append(" is not valid. Valid options are: ");
		for (SortOptions sortOption : SortOptions.values()) {
			optionDoesNotExistMessage.append(sortOption.getCode());
			optionDoesNotExistMessage.append(", ");
		}
		return optionDoesNotExistMessage.substring(0, optionDoesNotExistMessage.length() - 2);
	}
}
