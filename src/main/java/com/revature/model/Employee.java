package com.revature.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.exception.InvalidAccountTypeException;
import com.revature.exception.InvalidPasswordException;
import com.revature.exception.InvalidUsernameException;
import com.revature.exception.NameMustNotBeBlankException;
import com.revature.exception.NotValidEmailException;

public class Employee {
	private int id;


	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private int accountType;
	private int managerId;
	private boolean isManager;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws NameMustNotBeBlankException {
		if (firstName == null || firstName.equals("")) {
			throw new NameMustNotBeBlankException();
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws NameMustNotBeBlankException {
		if (lastName == null || lastName.equals("")) {
			throw new NameMustNotBeBlankException();
		}
		this.lastName = lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) throws InvalidUsernameException {
		if (username.length() < 4) {
			throw new InvalidUsernameException();
		}
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws InvalidPasswordException {
		if (password.length() < 8) {
			throw new InvalidPasswordException();
		}
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws NotValidEmailException {
		if (validate(email)) {
			this.email = email;
		} else {
			throw new NotValidEmailException();
		}

	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) throws InvalidAccountTypeException {
		if (accountType != 1 && accountType != 2) {
			throw new InvalidAccountTypeException();
		}
		this.accountType = accountType;
	}

	public Employee(String firstName, String lastName, String username, String password, String email, int accountType,
			int managerId) throws InvalidUsernameException, InvalidPasswordException, NotValidEmailException,
			InvalidAccountTypeException, NameMustNotBeBlankException {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setusername(username);
		setPassword(password);
		setEmail(email);
		setAccountType(accountType);
		this.managerId = managerId;
	}

	public Employee() {
		super();
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
