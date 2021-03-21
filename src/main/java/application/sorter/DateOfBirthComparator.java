package application.sorter;

import java.util.Comparator;

import application.model.Person;

/**
 * A comparator to be used to sort by Date of Birth in ascending order. Breaks
 * ties by using the internal OrderId property in ascending order.
 * 
 * @author tony
 *
 */
public class DateOfBirthComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		int comp = p1.getDateOfBirth().compareTo(p2.getDateOfBirth());
		if (comp == 0) {
			comp = ComparatorUtils.baseOrderComparator(p1, p2);
		}
		return comp;
	}
}
