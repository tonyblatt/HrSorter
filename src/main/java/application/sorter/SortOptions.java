package application.sorter;

import java.util.Optional;

/**
 * Enumeration of the different sorting options available. These items have a
 * code for mapping from user input to relevant sort options.
 * 
 * @author tony
 *
 */
public enum SortOptions {

	EMAIL("email"), BIRTH_DATE("birthdate"), LAST_NAME("name");

	/**
	 * User input for sort type.
	 */
	private String code;

	private SortOptions(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return The user input which this sort option represents.
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Maps the string (typically the user input) to the sort option which
	 * represents that user input. Maps to an empty optional if the user input is
	 * not one of the valid types.
	 * 
	 * @param code String to map to a Sort Option (based on the code property).
	 * @return The Sort Option which represents code, or an empty Optional if no
	 *         such code exists.
	 */
	public static Optional<SortOptions> getOptionFromCode(String code) {
		for (SortOptions option : SortOptions.values()) {
			if (option.code.equals(code)) {
				return Optional.of(option);
			}
		}
		return Optional.empty();
	}
}
