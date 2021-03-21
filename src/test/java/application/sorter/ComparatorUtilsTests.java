package application.sorter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import application.model.Person;

@SpringBootTest
public class ComparatorUtilsTests {

	@Test
	public void testBaseOrderComparator1() {
		Person p1 = new Person();
		p1.setOrderId(1);

		Person p2 = new Person();
		p2.setOrderId(2);

		Assert.assertEquals(-1, ComparatorUtils.baseOrderComparator(p1, p2));
	}

	@Test
	public void testBaseOrderComparator2() {
		Person p1 = new Person();
		p1.setOrderId(2);

		Person p2 = new Person();
		p2.setOrderId(1);

		Assert.assertEquals(1, ComparatorUtils.baseOrderComparator(p1, p2));
	}

	@Test
	public void testBaseOrderComparator3() {
		Person p1 = new Person();
		p1.setOrderId(1);

		Person p2 = new Person();
		p2.setOrderId(1);

		Assert.assertEquals(0, ComparatorUtils.baseOrderComparator(p1, p2));
	}
}
