package application.sorter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SortOptionsTests {

	@Test
	public void testGetOptionFromCodeActual() {
		for (SortOptions option : SortOptions.values()) {
			Assert.assertTrue(option == SortOptions.getOptionFromCode(option.getCode()).get());
		}
	}

	@Test
	public void testGetOptionFromCodeFake() {
		Assert.assertFalse(SortOptions.getOptionFromCode("not a real code").isPresent());
	}
}
