package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.ReimbursementRequest;
import com.revature.model.ReimbursementWrapper;



public interface ReimbursementRequestRepository {
	public void insertReimbursmentRequest(ReimbursementRequest reinbursmentRequest);
	public List<ReimbursementWrapper> getAllRequests();
	public List<ReimbursementRequest> getAllResolvedRequests();
	public byte[] getImage(int reimbursementRequestId );
	public List<ReimbursementRequest> getAllRequestByStatus(String status);
	public List<ReimbursementRequest> getReimbursementRequestsByEmployee(String username);
	public List<ReimbursementRequest> getPendingReimbursementRequestsByEmployee(String username);
	public List<ReimbursementRequest> getResolvedReimbursementRequestsByEmployee(String username);
	public void approve(ReimbursementRequest rr);
	public void deny(ReimbursementRequest rr);
	public ArrayList<ReimbursementWrapper> getManagersPendingEmployeeReimbursementRequests(int managerId);
	
	public void changeStatus(int reimbursmentImageId, int managerId, String string);

}
