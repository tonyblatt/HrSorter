package application.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

import application.constants.HrSorterConstants;
import application.model.Person;
import application.parser.LineParser;
import application.parser.LineParser.InvalidNumberOfItemsInLineException;
import application.sorter.PersonSorter;
import application.sorter.SortOptions;

@Controller
public class HrSorterController {

	private static List<Person> people = new ArrayList<>();
	private static final Gson GSON = new Gson();

	@PostMapping("/records")
	public ResponseEntity<String> postLine(@RequestBody String line) {
		try {
			people.add(LineParser.parseLine(people.size(), line, LineParser.findDelimiter(line)));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(String.format(HrSorterConstants.INVALID_DATE_FORMAT, line));
		} catch (InvalidNumberOfItemsInLineException e) {
			return ResponseEntity.badRequest().body(String.format(HrSorterConstants.INVALID_NUMBER_OF_ITEMS, line));
		}
		return ResponseEntity.ok("Successfully added line");
	}

	@GetMapping("/records/{sortOption}")
	public ResponseEntity<String> getLines(@PathVariable String sortOption) {
		Optional<SortOptions> parsedSortOption = SortOptions.getOptionFromCode(sortOption);
		if (!parsedSortOption.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		PersonSorter.sortPeople(people, parsedSortOption.get());
		return ResponseEntity.ok(GSON.toJson(people));
	}
}
