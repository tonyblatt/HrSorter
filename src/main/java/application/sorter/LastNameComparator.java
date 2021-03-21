package application.sorter;

import java.util.Comparator;

import application.model.Person;

/**
 * A comparator to be used to sort by Last Name in descending order. Breaks ties
 * by using the internal OrderId property in ascending order.
 * 
 * @author tony
 *
 */
public class LastNameComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		int comp = p2.getLastName().compareTo(p1.getLastName());
		if (comp == 0) {
			comp = ComparatorUtils.baseOrderComparator(p1, p2);
		}
		return comp;
	}
}
