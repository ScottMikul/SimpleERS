package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.EmployeeWrapper;
import com.revature.model.ReimbursementRequest;
import com.revature.model.ReimbursementWrapper;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.repository.ReimbursementRequestRepository;
import com.revature.repository.ReimbursementRequestRepositoryImpl;



public class EmployeeServices {
	private EmployeeRepository emplRepo;
	private ReimbursementRequestRepository rRepo;
	public EmployeeServices() {
		emplRepo = new EmployeeRepositoryImpl();
		rRepo = new ReimbursementRequestRepositoryImpl();
	}

	// employee
	//handles employee and manager login
	
	//1
	public Employee login(String username, String password) {
		
		return emplRepo.logIn(username, password);
	}
	//2
	public void logout() {
		
	}
	//3
	public void AddNewReimbursementRequest(ReimbursementRequest rr) {
		rRepo.insertReimbursmentRequest(rr);
	}
	//4
	public Employee viewInfo(int empId) {
		return emplRepo.getProfileInfo(empId);
	}
	//5
	public void setInfo( Employee emp) {
		emplRepo.updateEmployeProfileInfo(emp);
	}
	//6
	public List <ReimbursementRequest> viewPendingReimbursementRequests(String employeeUsername){
		return rRepo.getPendingReimbursementRequestsByEmployee(employeeUsername );
	}
	//7
	public List <ReimbursementRequest> viewReimbursementRequests(String employeeUsername){
		return rRepo.getReimbursementRequestsByEmployee(employeeUsername );
	}
	//8
	public List <ReimbursementRequest> viewResolvedReimbursmentRequests(String employeeUsername){
		return rRepo.getResolvedReimbursementRequestsByEmployee(employeeUsername );
		
	}
	//9
	public void ApproveReimbursementRequest(ReimbursementRequest rr) {
		rRepo.approve(rr);
	}
	//10
	public void denyReimbursementRequest(ReimbursementRequest reimbursmentImageId) {
		rRepo.deny(reimbursmentImageId);
	}
	//11
	public List <Employee> viewAllEmployees(Employee manager){
		return emplRepo.getAllManagerEmployees(manager);
	}
	//12 //15 username null check
	public List <ReimbursementRequest> viewPendingReimbursementRequests(Employee employee){
		if(employee.getusername()==null) {
			return null;
		}
		return rRepo.getPendingReimbursementRequestsByEmployee(employee.getusername());
	}
	//13 //16 username null check
	public List <ReimbursementRequest> viewReimbursementRequests( Employee employee){
		if(employee.getusername()==null) {
			return null;
		}
		return rRepo.getReimbursementRequestsByEmployee(employee.getusername() );
	}
	//14 // 17 username null check
	public List <ReimbursementRequest> viewResolvedReimbursmentRequests( Employee employee){
		if(employee.getusername()==null) {
			return null;
		}
		return rRepo.getResolvedReimbursementRequestsByEmployee(employee.getusername() );
		
	}
	
	
	
	
	
	public List <ReimbursementRequest> getAllResolvedReimbursementRequests(){
		return rRepo.getAllRequestByStatus("resolved");
	}
	
	public List <ReimbursementRequest> getAllApprovedReimbursementRequests(){
		return rRepo.getAllRequestByStatus("approved");
	}
	public List <ReimbursementRequest> getAllDeniedReimbursementRequests(){
		return rRepo.getAllRequestByStatus("denied");
	}
	public List <ReimbursementRequest> getAllPendingReimbursementRequests(){
		return rRepo.getAllRequestByStatus("pending");
	}
	public List<EmployeeWrapper> getAllEmployees(){
		return emplRepo.getAllEmployees();
		
	}
	public byte[] getImage(int imageId) {
		return rRepo.getImage(imageId);
		
	}

	public ArrayList<ReimbursementWrapper> getAllReimbursementRequests() {
		// TODO Auto-generated method stub
		return (ArrayList<ReimbursementWrapper>) rRepo.getAllRequests();
	}
	
	public ArrayList<ReimbursementWrapper> managersEmployeesRequests(int ManagerId) {
		// TODO Auto-generated method stub
		return (ArrayList<ReimbursementWrapper>) rRepo.getManagersPendingEmployeeReimbursementRequests(ManagerId);
	}

	public void approveReimbursementRequest(int reimbursmentImageId, int managerId) {
		// TODO Auto-generated method stub
		System.out.println("reimbursment id "+ reimbursmentImageId);
		System.out.println("managerId id "+ managerId);
		
		rRepo.changeStatus( reimbursmentImageId, managerId, "approved");
	}

	public void denyReimbursementRequest(int reimbursmentImageId, int managerId) {
		// TODO Auto-generated method stub
		rRepo.changeStatus( reimbursmentImageId, managerId, "approved");
	}
	
}
