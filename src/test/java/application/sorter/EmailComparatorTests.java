package application.sorter;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import application.model.Person;

@SpringBootTest
public class EmailComparatorTests {

	private Comparator<Person> comparator = new EmailComparator();

	@Test
	public void testCompareEmailsDifferent1() {
		Person p1 = new Person();
		p1.setEmail("a");

		Person p2 = new Person();
		p2.setEmail("b");

		Assert.assertEquals(1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareEmailsDifferent2() {
		Person p1 = new Person();
		p1.setEmail("b");

		Person p2 = new Person();
		p2.setEmail("a");

		Assert.assertEquals(-1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareEmailsSameLastNameDifferent1() {
		Person p1 = new Person();
		p1.setEmail("a");
		p1.setLastName("a");

		Person p2 = new Person();
		p2.setEmail("a");
		p2.setLastName("b");

		Assert.assertEquals(-1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareEmailsSameLastNameDifferent2() {
		Person p1 = new Person();
		p1.setEmail("a");
		p1.setLastName("b");

		Person p2 = new Person();
		p2.setEmail("a");
		p2.setLastName("a");

		Assert.assertEquals(1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareTieBreak1() {
		Person p1 = new Person();
		p1.setEmail("a");
		p1.setLastName("a");
		p1.setOrderId(1);

		Person p2 = new Person();
		p2.setEmail("a");
		p2.setLastName("a");
		p2.setOrderId(2);

		Assert.assertEquals(-1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareTieBreak2() {
		Person p1 = new Person();
		p1.setEmail("a");
		p1.setLastName("a");
		p1.setOrderId(2);

		Person p2 = new Person();
		p2.setEmail("a");
		p2.setLastName("a");
		p2.setOrderId(1);

		Assert.assertEquals(1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareAllSame() {
		Person p1 = new Person();
		p1.setEmail("a");
		p1.setLastName("a");
		p1.setOrderId(1);

		Person p2 = new Person();
		p2.setEmail("a");
		p2.setLastName("a");
		p2.setOrderId(1);

		Assert.assertEquals(0, comparator.compare(p1, p2));
	}
}
