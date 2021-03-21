package application.model;

import org.joda.time.LocalDate;

import com.google.gson.annotations.JsonAdapter;

import application.adapters.DateJsonAdapter;

/**
 * A model class representing a person.
 * 
 * @author tony
 *
 */
public class Person {

	private int orderId;

	private String lastName;

	private String firstName;

	private String email;

	private String favoriteColor;

	@JsonAdapter(DateJsonAdapter.class)
	private LocalDate dateOfBirth;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFavoriteColor() {
		return favoriteColor;
	}

	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
