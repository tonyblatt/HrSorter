package application.sorter;

import java.util.Comparator;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import application.model.Person;

@SpringBootTest
public class DateOfBirthComparatorTests {

	private Comparator<Person> comparator = new DateOfBirthComparator();
	private LocalDate today = LocalDate.now();
	private LocalDate in5Days;

	@BeforeEach
	public void init() {
		in5Days = today.plusDays(5);
	}

	@Test
	public void testCompareDifferent1() {
		Person p1 = new Person();
		p1.setDateOfBirth(today);

		Person p2 = new Person();
		p2.setDateOfBirth(in5Days);

		Assert.assertEquals(-1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareDifferent2() {
		Person p1 = new Person();
		p1.setDateOfBirth(in5Days);

		Person p2 = new Person();
		p2.setDateOfBirth(today);

		Assert.assertEquals(1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareTieBreak1() {
		Person p1 = new Person();
		p1.setDateOfBirth(today);
		p1.setOrderId(1);

		Person p2 = new Person();
		p2.setDateOfBirth(today);
		p2.setOrderId(2);

		Assert.assertEquals(-1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareTieBreak2() {
		Person p1 = new Person();
		p1.setDateOfBirth(today);
		p1.setOrderId(2);

		Person p2 = new Person();
		p2.setDateOfBirth(today);
		p2.setOrderId(1);

		Assert.assertEquals(1, comparator.compare(p1, p2));
	}

	@Test
	public void testCompareAllSame() {
		Person p1 = new Person();
		p1.setDateOfBirth(today);
		p1.setOrderId(1);

		Person p2 = new Person();
		p2.setDateOfBirth(today);
		p2.setOrderId(1);

		Assert.assertEquals(0, comparator.compare(p1, p2));
	}
}
