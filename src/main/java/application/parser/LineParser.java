package application.parser;

import java.text.ParseException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import application.model.Person;

/**
 * Utils for parsing through lines of a file.
 * 
 * @author tony
 *
 */
public class LineParser {

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("M/D/YYYY");

	/**
	 * Determines the delimiter being used by a specific line.
	 * 
	 * @param line The line being tested.
	 * @return The delimiter which can be used to split the line.
	 */
	public static String findDelimiter(String line) {
		if (line.contains("|")) {
			return "\\|";
		}

		if (line.contains(",")) {
			return ",";
		}

		return " ";
	}

	/**
	 * Parses a line into a Person.
	 * 
	 * @param index     The index of the line.
	 * @param line      The line proper.
	 * @param delimiter The delimiter of the line.
	 * @return The person the line represents.
	 * @throws ParseException                      The Date of Birth does not parse.
	 * @throws InvalidNumberOfItemsInLineException There are not exactly 5 elements
	 *                                             in the line.
	 */
	public static Person parseLine(int index, String line, String delimiter)
			throws IllegalArgumentException, InvalidNumberOfItemsInLineException {
		String[] split = line.split(delimiter);
		if (split.length != 5) {
			throw new InvalidNumberOfItemsInLineException();
		}

		LocalDate dateOfBirth = DATE_FORMAT.parseLocalDate(split[4]);

		Person ret = new Person();
		ret.setOrderId(index);
		ret.setLastName(split[0]);
		ret.setFirstName(split[1]);
		ret.setEmail(split[2]);
		ret.setFavoriteColor(split[3]);
		ret.setDateOfBirth(dateOfBirth);

		return ret;
	}

	public static class InvalidNumberOfItemsInLineException extends Exception {
	}
}
