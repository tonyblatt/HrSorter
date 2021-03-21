package application.sorter;

import java.util.Comparator;

import application.model.Person;

/**
 * A comparator to be used to sort by Email in descending order. Breaks ties by
 * using the Last Name property in ascending order. Breaks still further ties by
 * using the internal OrderId property in ascending order.
 * 
 * @author tony
 *
 */
public class EmailComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		int comp = p2.getEmail().compareTo(p1.getEmail());
		if (comp == 0) {
			comp = p1.getLastName().compareTo(p2.getLastName());
		}

		if (comp == 0) {
			comp = ComparatorUtils.baseOrderComparator(p1, p2);
		}
		return comp;
	}
}
