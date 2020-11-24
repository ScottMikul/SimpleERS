package com.revature.model;

public class EmployeeWrapper {
	String employeeFullName;
	String email;
	String username;
	String managerFullName;
	public String getEmployeeFullName() {
		return employeeFullName;
	}
	public void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getManagerFullName() {
		return managerFullName;
	}
	public void setManagerFullName(String managerFullName) {
		this.managerFullName = managerFullName;
	}
}
