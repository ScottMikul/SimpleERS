package com.revature.services;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.revature.exception.InvalidUsernameException;
import com.revature.model.Employee;
import com.revature.model.ReimbursementRequest;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.repository.ReimbursementRequestRepository;
import com.revature.repository.ReimbursementRequestRepositoryImpl;

public class testServices {
	
	 @Mock static EmployeeRepository empRepo;
	
	@Mock
	static ReimbursementRequestRepository RRepo;
	
	
	@InjectMocks
	static EmployeeServices service;

	@BeforeClass
	public static void setUp(){
		RRepo = new ReimbursementRequestRepositoryImpl();
		empRepo = new EmployeeRepositoryImpl();
		service = new EmployeeServices();
	}

	@Before
	public void perMethodSetUp() {
		MockitoAnnotations.openMocks(this);
	}


	
	//employee tests

	
	@Test
	public void setEmployeeInfoTest(){
		Mockito.doNothing().when(empRepo).updateEmployeProfileInfo(Mockito.any());
		service.setInfo(new Employee());
		Mockito.verify(empRepo).updateEmployeProfileInfo(Mockito.any());
	}
	
	@Test
	public void viewAllEmployeesTest(){
		Mockito.doReturn(new ArrayList<Employee> ()).when(empRepo).getAllEmployees();
		service.getAllEmployees();
		Mockito.verify(empRepo).getAllEmployees();
	}
	
	@Test
	public void viewAllManagerEmployeesTest(){
		Mockito.doReturn(new ArrayList<Employee>()).when(empRepo).getAllManagerEmployees(Mockito.any());
		service.viewAllEmployees(new Employee());
		Mockito.verify(empRepo).getAllManagerEmployees(Mockito.any());
	}
	@Test
	public void viewProfileInfo(){
		Mockito.doReturn(new Employee()).when(empRepo).getProfileInfo(Mockito.anyInt());
		service.viewInfo(1);
		Mockito.verify(empRepo).getProfileInfo(Mockito.anyInt());
	}
	
	//reimbursement tests
	
	@Test
	public void addReimbursementAccount(){
		Mockito.doNothing().when(RRepo).insertReimbursmentRequest(Mockito.any());
		service.AddNewReimbursementRequest(new ReimbursementRequest());
		Mockito.verify(RRepo).insertReimbursmentRequest(Mockito.any());
	}
	
	@Test
	public void approveReimbursementTest(){
		Mockito.doNothing().when(RRepo).approve(Mockito.any());
		service.ApproveReimbursementRequest(new ReimbursementRequest());
		Mockito.verify(RRepo).approve(Mockito.any());
	}
	@Test
	public void denyReimbursementTest(){
		Mockito.doNothing().when(RRepo).deny(Mockito.any());
		service.denyReimbursementRequest(new ReimbursementRequest());
		Mockito.verify(RRepo).deny(Mockito.any());
		
	}
	
	@Test
	public void viewAllResolvedReimbursementTest(){
		Mockito.doReturn(new ArrayList<ReimbursementRequest>()).when(RRepo).getAllRequestByStatus(Mockito.anyString());
		service.getAllResolvedReimbursementRequests();
		Mockito.verify(RRepo).getAllRequestByStatus(Mockito.anyString());
		
	}
	
	@Test
	public void viewAllPendingReimbursementTest(){
		Mockito.doReturn(new ArrayList<ReimbursementRequest>()).when(RRepo).getAllRequestByStatus(Mockito.anyString());
		service.getAllPendingReimbursementRequests();
		Mockito.verify(RRepo).getAllRequestByStatus(Mockito.anyString());
	
	}
	@Test
	public void viewAllApprovedReimbursementTest(){
		Mockito.doReturn(new ArrayList<ReimbursementRequest>()).when(RRepo).getAllRequestByStatus(Mockito.anyString());
		service.getAllApprovedReimbursementRequests();
		Mockito.verify(RRepo).getAllRequestByStatus(Mockito.anyString());
	
	}
	@Test
	public void viewAllDeniedReimbursementTest(){
		Mockito.doReturn(new ArrayList<ReimbursementRequest>()).when(RRepo).getAllRequestByStatus(Mockito.anyString());
		service.getAllDeniedReimbursementRequests();
		Mockito.verify(RRepo).getAllRequestByStatus(Mockito.anyString());
	
	}
	
	@Test
	public void viewAllEmployeeReimbursementRequests() throws InvalidUsernameException {
		Mockito.doReturn(new ArrayList<ReimbursementRequest>()).when(RRepo).getResolvedReimbursementRequestsByEmployee(Mockito.anyString());
		Employee temp = new Employee();
		temp.setusername("temporary");
		service.viewReimbursementRequests(temp);
		Mockito.verify(RRepo).getReimbursementRequestsByEmployee(Mockito.anyString());
	}
	
	@Test
	public void viewAllEmployeePendingReimbursementRequest() throws InvalidUsernameException {
		Mockito.doReturn(new ArrayList<ReimbursementRequest>()).when(RRepo).getPendingReimbursementRequestsByEmployee(Mockito.anyString());
		Employee temp = new Employee();
		temp.setusername("temporary");
		service.viewPendingReimbursementRequests(temp);
		Mockito.verify(RRepo).getPendingReimbursementRequestsByEmployee(Mockito.anyString());
	}
	
	
	@Test
	public void viewResovledEmployeeRequests() throws InvalidUsernameException {
		Mockito.doReturn(new ArrayList<ReimbursementRequest>()).when(RRepo).getResolvedReimbursementRequestsByEmployee(Mockito.anyString());
		Employee temp = new Employee();
		temp.setusername("temporary");
		service.viewResolvedReimbursmentRequests(temp);
		Mockito.verify(RRepo).getResolvedReimbursementRequestsByEmployee(Mockito.anyString());
	}
	@After
	public void perMethodTearDown() {
		
	}
	

	@AfterClass
	public static void tearDown() {
	}
}
