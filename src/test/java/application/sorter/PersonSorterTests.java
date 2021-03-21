package application.sorter;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import application.model.Person;

@SpringBootTest
public class PersonSorterTests {

	private List<Person> testPeople;
	private LocalDate now = LocalDate.now();

	@BeforeEach
	public void init() {
		testPeople = new ArrayList<>(10);

		Person p = new Person();
		p.setOrderId(1);
		p.setDateOfBirth(now);
		p.setEmail("a");
		p.setFavoriteColor("a");
		p.setFirstName("a");
		p.setLastName("d");
		testPeople.add(p);

		p = new Person();
		p.setOrderId(0);
		p.setDateOfBirth(now);
		p.setEmail("a");
		p.setFavoriteColor("a");
		p.setFirstName("a");
		p.setLastName("b");
		testPeople.add(p);

		p = new Person();
		p.setOrderId(2);
		p.setDateOfBirth(now.plusDays(1));
		p.setEmail("b");
		p.setFavoriteColor("a");
		p.setFirstName("a");
		p.setLastName("d");
		testPeople.add(p);

		p = new Person();
		p.setOrderId(3);
		p.setDateOfBirth(now.minusDays(1));
		p.setEmail("c");
		p.setFavoriteColor("a");
		p.setFirstName("a");
		p.setLastName("b");
		testPeople.add(p);

		p = new Person();
		p.setOrderId(4);
		p.setDateOfBirth(now.minusDays(1));
		p.setEmail("c");
		p.setFavoriteColor("a");
		p.setFirstName("a");
		p.setLastName("a");
		testPeople.add(p);
	}

	@Test
	public void testSortPeopleByEmail() {
		PersonSorter.sortPeople(testPeople, SortOptions.EMAIL);

		List<Integer> expected = Lists.list((Integer) 4, (Integer) 3, (Integer) 2, (Integer) 0, (Integer) 1);

		for (int i = 0; i < testPeople.size(); i++) {
			Assert.assertEquals(expected.get(i).intValue(), testPeople.get(i).getOrderId());
		}
	}

	@Test
	public void testSortPeopleByDateOfBirth() {
		PersonSorter.sortPeople(testPeople, SortOptions.BIRTH_DATE);

		List<Integer> expected = Lists.list((Integer) 3, (Integer) 4, (Integer) 0, (Integer) 1, (Integer) 2);

		for (int i = 0; i < testPeople.size(); i++) {
			Assert.assertEquals(expected.get(i).intValue(), testPeople.get(i).getOrderId());
		}
	}

	@Test
	public void testSortPeopleByLastName() {
		PersonSorter.sortPeople(testPeople, SortOptions.LAST_NAME);

		List<Integer> expected = Lists.list((Integer) 1, (Integer) 2, (Integer) 0, (Integer) 3, (Integer) 4);

		for (int i = 0; i < testPeople.size(); i++) {
			Assert.assertEquals(expected.get(i).intValue(), testPeople.get(i).getOrderId());
		}
	}
}
