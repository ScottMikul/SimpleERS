package com.revature.models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.exception.DescriptionMustNotBeBlankExcpetion;
import com.revature.exception.InvalidAccountTypeException;
import com.revature.exception.InvalidAmountException;
import com.revature.exception.InvalidPasswordException;
import com.revature.exception.InvalidReimbursementStatusException;
import com.revature.exception.InvalidUsernameException;
import com.revature.exception.NameMustNotBeBlankException;
import com.revature.exception.NotValidEmailException;
import com.revature.model.Employee;
import com.revature.model.ReimbursementRequest;


public class testModels {
	
	Employee Empl;
	ReimbursementRequest RR;

	@BeforeClass
	public static void setUp(){
		
	}

	@Before
	public void perMethodSetUp() {
		Empl = null;
		RR = null;
	}

	@Test
	public void employeeHasEmptyConstructor(){
		Empl = new Employee();
	}
	
	@Test
	public void employeeHasAlternativeConstructor(){
		try {
			Empl = new Employee("scotty","mikul", "username","password1","email@email.com",1,1);
		} catch (InvalidUsernameException | InvalidPasswordException | NotValidEmailException
				| InvalidAccountTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NameMustNotBeBlankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test 
	public void testFullName() throws InvalidUsernameException, InvalidPasswordException, NotValidEmailException, InvalidAccountTypeException, NameMustNotBeBlankException{
		Empl = new Employee("scotty","mikul", "username","password1","email@email.com",1,1);
		assertEquals("scotty mikul" , Empl.getFullName());
	}

	@Test
	public void ReimbursementRequestHasEmptyConstructor(){
		RR = new ReimbursementRequest();
	}
	
	@Test
	public void ReimbursementRequestHasAlternativeConstructor() throws InvalidAmountException, DescriptionMustNotBeBlankExcpetion, InvalidReimbursementStatusException{
		RR = new ReimbursementRequest(null,1,"description","PENDING",1);
	}
	@Test(expected = InvalidAmountException.class)
	public void testInvalidAmount() throws InvalidAmountException, DescriptionMustNotBeBlankExcpetion, InvalidReimbursementStatusException{
		RR = new ReimbursementRequest(null,-1,"description","PENDING",1);
	}
	@Test(expected = DescriptionMustNotBeBlankExcpetion.class)
	public void testBlankDescription() throws InvalidAmountException, DescriptionMustNotBeBlankExcpetion, InvalidReimbursementStatusException{
		RR = new ReimbursementRequest(null,1,"","PENDING",1);
	}
	@Test(expected = InvalidReimbursementStatusException.class)
	public void testInvalidStatusException() throws InvalidAmountException, DescriptionMustNotBeBlankExcpetion, InvalidReimbursementStatusException{
		RR = new ReimbursementRequest(null,1,"description","invalid",1);
	}
	
	
	@Test(expected = InvalidUsernameException.class)
	public void invalidUsernameTest() throws InvalidUsernameException, InvalidPasswordException, NotValidEmailException, InvalidAccountTypeException, NameMustNotBeBlankException{
			Empl = new Employee("scotty","mikul","a","password1","email@email.com",1,1);

	}
	@Test(expected = InvalidPasswordException.class)
	public void invalidPasswordTest() throws InvalidPasswordException, InvalidUsernameException, NotValidEmailException, InvalidAccountTypeException, NameMustNotBeBlankException{
			Empl = new Employee("scotty","mikul","username","weak","email@email.com",1,1);
	}
	
	@Test(expected = NotValidEmailException.class)
	public void notValidEmailTest() throws InvalidPasswordException, InvalidUsernameException, NotValidEmailException, InvalidAccountTypeException, NameMustNotBeBlankException{
		Empl = new Employee("scotty","mikul","username","password1","invalid!email.com",1,1);
	}
	@Test(expected = InvalidAccountTypeException.class)
	public void notValidAccountType() throws InvalidPasswordException, InvalidUsernameException, NotValidEmailException, InvalidAccountTypeException, NameMustNotBeBlankException{
		Empl = new Employee("scotty","mikul","username","password1","invalid@email.com",30,1);
	}
	
	@Test(expected = NameMustNotBeBlankException.class)
	public void testBlankFirstName() throws InvalidPasswordException, InvalidUsernameException, NotValidEmailException, InvalidAccountTypeException, NameMustNotBeBlankException{
		Empl = new Employee("","mikul","username","password1","invalid@email.com",30,1);
	}
	
	@Test(expected = NameMustNotBeBlankException.class)
	public void testBlankLastName() throws InvalidPasswordException, InvalidUsernameException, NotValidEmailException, InvalidAccountTypeException, NameMustNotBeBlankException{
		Empl = new Employee("Scotty","","username","password1","invalid@email.com",30,1);
	}
	@After
	public void perMethodTearDown() {
		
	}
	

	@AfterClass
	public static void tearDown() {
	}
}
