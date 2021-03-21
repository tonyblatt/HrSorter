package application.sorter;

import application.model.Person;

/**
 * Utility class for comparators.
 * 
 * @author tony
 *
 */
public class ComparatorUtils {

	/**
	 * A function for comparing people by their Order Id. The comparison fits with
	 * ascending order.
	 * 
	 * @param p1 The left person to compare.
	 * @param p2 The right person to compare.
	 * @return The ascending order comparison of p1 and p2 by their Order Ids.
	 */
	public static int baseOrderComparator(Person p1, Person p2) {
		return ((Integer) p1.getOrderId()).compareTo(p2.getOrderId());
	}
}
