package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.EmployeeWrapper;




public interface EmployeeRepository {
	public Employee logIn(String username , String password);
	public List <EmployeeWrapper> getAllEmployees();
	public Employee getProfileInfo(int EmployeeId);
	public void updateEmployeProfileInfo(Employee empl);
	public List <Employee> getAllManagerEmployees(Employee manager); 

}
