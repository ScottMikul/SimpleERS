package com.revature.model;

public class ReimbursementWrapper {
	//r.amount,r.description,r.status,r.hasImage, r.id, r.employeeid, r.resolver , e.first_name, e.last_name
	private double amount;
	private String description;
	private String status;
	private boolean hasImage;
	private int reimbursementId;
	private int employeeid;
	private int resolverid;
	private String employeeFullName;
	private String resovlerFullName;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isHasImage() {
		return hasImage;
	}
	public void setHasImage(boolean hasImage) {
		this.hasImage = hasImage;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public int getResolverid() {
		return resolverid;
	}
	public void setResolverid(int resolverid) {
		this.resolverid = resolverid;
	}
	public String getEmployeeFullName() {
		return employeeFullName;
	}
	public void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}
	public String getResovlerFullName() {
		return resovlerFullName;
	}
	public void setResovlerFullName(String resovlerFullName) {
		this.resovlerFullName = resovlerFullName;
	}
}
