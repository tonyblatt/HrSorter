package application.parser;

import java.text.ParseException;
import java.util.StringJoiner;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import application.model.Person;
import application.parser.LineParser.InvalidNumberOfItemsInLineException;

@SpringBootTest
public class LineParserTests {

	@Test
	public void testFindDelimiterPipe() {
		Assert.assertEquals("\\|", LineParser.findDelimiter("a|l"));
	}

	@Test
	public void testFindDelimiterComma() {
		Assert.assertEquals(",", LineParser.findDelimiter("a,l"));
	}

	@Test
	public void testFindDelimiterSpace() {
		Assert.assertEquals(" ", LineParser.findDelimiter("a l"));
	}

	@Test
	public void testParseLinePipes() throws ParseException, InvalidNumberOfItemsInLineException {
		String delimiter = "\\|";

		String lastName = "lastName";
		String firstName = "firstName";
		String email = "email";
		String favoriteColor = "favoriteColor";
		String dateOfBirth = "3/7/2010";

		String line = new StringJoiner("|").add(lastName).add(firstName).add(email).add(favoriteColor).add(dateOfBirth)
				.toString();

		Person p = LineParser.parseLine(1, line, delimiter);

		LocalDate expectedDate = LineParser.DATE_FORMAT.parseLocalDate(dateOfBirth);

		Assert.assertEquals(1, p.getOrderId());
		Assert.assertEquals(lastName, p.getLastName());
		Assert.assertEquals(firstName, p.getFirstName());
		Assert.assertEquals(email, p.getEmail());
		Assert.assertEquals(favoriteColor, p.getFavoriteColor());
		Assert.assertEquals(expectedDate, p.getDateOfBirth());
	}

	@Test
	public void testParseLineCommas() throws ParseException, InvalidNumberOfItemsInLineException {
		String delimiter = ",";

		String lastName = "lastName";
		String firstName = "firstName";
		String email = "email";
		String favoriteColor = "favoriteColor";
		String dateOfBirth = "3/7/2010";

		String line = new StringJoiner(delimiter).add(lastName).add(firstName).add(email).add(favoriteColor)
				.add(dateOfBirth).toString();

		Person p = LineParser.parseLine(1, line, delimiter);

		LocalDate expectedDate = LineParser.DATE_FORMAT.parseLocalDate(dateOfBirth);

		Assert.assertEquals(1, p.getOrderId());
		Assert.assertEquals(lastName, p.getLastName());
		Assert.assertEquals(firstName, p.getFirstName());
		Assert.assertEquals(email, p.getEmail());
		Assert.assertEquals(favoriteColor, p.getFavoriteColor());
		Assert.assertEquals(expectedDate, p.getDateOfBirth());
	}

	@Test
	public void testParseLineSpaces() throws ParseException, InvalidNumberOfItemsInLineException {
		String delimiter = " ";

		String lastName = "lastName";
		String firstName = "firstName";
		String email = "email";
		String favoriteColor = "favoriteColor";
		String dateOfBirth = "3/7/2010";

		String line = new StringJoiner(delimiter).add(lastName).add(firstName).add(email).add(favoriteColor)
				.add(dateOfBirth).toString();

		Person p = LineParser.parseLine(1, line, delimiter);

		LocalDate expectedDate = LineParser.DATE_FORMAT.parseLocalDate(dateOfBirth);

		Assert.assertEquals(1, p.getOrderId());
		Assert.assertEquals(lastName, p.getLastName());
		Assert.assertEquals(firstName, p.getFirstName());
		Assert.assertEquals(email, p.getEmail());
		Assert.assertEquals(favoriteColor, p.getFavoriteColor());
		Assert.assertEquals(expectedDate, p.getDateOfBirth());
	}

	@Test
	public void testParseLineNotEnoughItems() {
		Assert.assertThrows(InvalidNumberOfItemsInLineException.class, () -> {
			LineParser.parseLine(0, "", ",");
		});
	}

	@Test
	public void testParseLineBadDate() {
		Assert.assertThrows(IllegalArgumentException.class, () -> {
			String delimiter = " ";

			String lastName = "lastName";
			String firstName = "firstName";
			String email = "email";
			String favoriteColor = "favoriteColor";
			String dateOfBirth = "blahs";

			String line = new StringJoiner(delimiter).add(lastName).add(firstName).add(email).add(favoriteColor)
					.add(dateOfBirth).toString();

			LineParser.parseLine(1, line, delimiter);
		});
	}

	@Test
	public void testParseLineMissingItems() throws ParseException, InvalidNumberOfItemsInLineException {
		String delimiter = " ";

		String lastName = "lastName";
		String firstName = "";
		String email = "email";
		String favoriteColor = "favoriteColor";
		String dateOfBirth = "3/7/2010";

		String line = new StringJoiner(delimiter).add(lastName).add(firstName).add(email).add(favoriteColor)
				.add(dateOfBirth).toString();

		Person p = LineParser.parseLine(1, line, delimiter);

		LocalDate expectedDate = LineParser.DATE_FORMAT.parseLocalDate(dateOfBirth);

		Assert.assertEquals(1, p.getOrderId());
		Assert.assertEquals(lastName, p.getLastName());
		Assert.assertEquals(firstName, p.getFirstName());
		Assert.assertEquals(email, p.getEmail());
		Assert.assertEquals(favoriteColor, p.getFavoriteColor());
		Assert.assertEquals(expectedDate, p.getDateOfBirth());
	}
}
