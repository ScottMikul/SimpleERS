package com.revature.model;

import com.revature.exception.DescriptionMustNotBeBlankExcpetion;
import com.revature.exception.InvalidAmountException;
import com.revature.exception.InvalidReimbursementStatusException;

public class ReimbursementRequest {
	private byte[] image;
	private double amount;
	private String description;
	private String status;
	private int resolver;
	private int employeeId;
	private int id;
	private boolean hasImage;
	public boolean hasImage() {
		return hasImage;
	}
	public void setHasImage(boolean hasImage) {
		this.hasImage = hasImage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) throws InvalidReimbursementStatusException {
		if(!status.equals("pending")&&!status.equals("rejected")&&!status.equals("approved")) {
			throw new InvalidReimbursementStatusException();
		}
		this.status = status;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) throws InvalidAmountException {
		if(amount<0) {
			throw new InvalidAmountException();
		}
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public ReimbursementRequest() {
		super();
	}
	public ReimbursementRequest(byte[] image, int amount, String description, String status, int resolver) throws InvalidAmountException, DescriptionMustNotBeBlankExcpetion, InvalidReimbursementStatusException {
		super();
		this.image = image;
		setAmount(amount);
		setDescription(description);
		setStatus(status);
		this.resolver = resolver;
	}
	public void setDescription(String description) throws DescriptionMustNotBeBlankExcpetion {
		if(description.isEmpty()) {
			throw new DescriptionMustNotBeBlankExcpetion();
		}
		this.description = description;
	}
	

}
