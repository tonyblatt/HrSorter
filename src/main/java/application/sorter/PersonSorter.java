package application.sorter;

import java.util.List;

import application.model.Person;

/**
 * ' Utils for sorting people.
 * 
 * @author tony
 *
 */
public class PersonSorter {

	/**
	 * Sorts a list of people based on the sort option provided. (Sort is done in
	 * place and reflects on the list parameter.)
	 * 
	 * @param people     List of people to sort.
	 * @param sortOption Option for sorting people
	 */
	public static void sortPeople(List<Person> people, SortOptions sortOption) {
		switch (sortOption) {
		case EMAIL:
			people.sort(new EmailComparator());
			break;
		case BIRTH_DATE:
			people.sort(new DateOfBirthComparator());
			break;
		case LAST_NAME:
			people.sort(new LastNameComparator());
			break;
		default:
			throw new RuntimeException("Not a valid sort option: " + sortOption.toString());
		}
	}
}
