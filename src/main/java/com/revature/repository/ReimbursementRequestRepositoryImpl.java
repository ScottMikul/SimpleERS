package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exception.DescriptionMustNotBeBlankExcpetion;
import com.revature.exception.InvalidAmountException;
import com.revature.exception.InvalidReimbursementStatusException;
import com.revature.model.ReimbursementRequest;
import com.revature.model.ReimbursementWrapper;
import com.revature.utilities.ConnectionCloser;
import com.revature.utilities.ConnectionFactory;


public class ReimbursementRequestRepositoryImpl implements ReimbursementRequestRepository{

	@Override
	public void insertReimbursmentRequest(ReimbursementRequest rr) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("insert into reimbursementrequest (employeeid , status,image, amount,description , resolver, hasimage) values (?,'pending',?,?,?,null, ?)"  );
			stmt.setInt(1, rr.getEmployeeId());
			stmt.setBytes(2,rr.getImage());
			stmt.setDouble(3, rr.getAmount());
			stmt.setString(4, rr.getDescription());
			stmt.setBoolean(5, rr.hasImage());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionCloser.closeResource(conn);
			ConnectionCloser.closeResource(stmt);
		}
	}

	@Override
	public List<ReimbursementWrapper> getAllRequests() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ArrayList<ReimbursementWrapper> rrs = new ArrayList<>();
			stmt = conn.prepareStatement("select r.amount,r.description,r.status,r.hasImage, r.id, r.employeeid, r.resolver , e.first_name as \"resolverf\", e.last_name as \"resolverl\" ,e2.first_name as \"efirst\",e2.last_name as \"elast\" from reimbursementrequest r left outer join employee e on r.resolver = e.id inner join employee e2 on r.employeeid = e2.id");
			ResultSet rset = stmt.executeQuery();
			while(rset.next()) {
				ReimbursementWrapper rr = new ReimbursementWrapper();
				rr.setAmount(rset.getDouble("amount"));
				rr.setDescription(rset.getString("description"));
				rr.setHasImage(rset.getBoolean("hasImage"));
				rr.setReimbursementId(rset.getInt("id"));
				rr.setStatus(rset.getString("status"));
				rr.setEmployeeid(rset.getInt("employeeid"));
				rr.setResolverid(rset.getInt("resolver"));
				rr.setEmployeeFullName(rset.getString("efirst")+ " "+ rset.getString("elast"));
				rr.setResovlerFullName(rset.getString("resolverf")+ " " + rset.getString("resolverl"));
				rrs.add(rr);
			}
			return rrs;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			ConnectionCloser.closeResource(conn);
			ConnectionCloser.closeResource(stmt);
		}
		return null;
	}

	@Override
	public byte[] getImage(int reimbursementRequestId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("select image from reimbursementrequest where id = ? "  );
			stmt.setInt(1, reimbursementRequestId);
			
			ResultSet set = stmt.executeQuery();
			
			if(set.next()) {
				byte[] image = set.getBytes("image");
				return image;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionCloser.closeResource(conn);
			ConnectionCloser.closeResource(stmt);
		}
		return null;
	}

	@Override
	public List<ReimbursementRequest> getAllRequestByStatus(String status) {
		// TODO Auto-generated method stub
		
		if(status.equals("resolved")) {
			
		}
		return null;
	}


	@Override
	public List<ReimbursementRequest> getAllResolvedRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approve(ReimbursementRequest rr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deny(ReimbursementRequest rr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReimbursementRequest> getReimbursementRequestsByEmployee(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ArrayList<ReimbursementRequest> rrs = new ArrayList<>();
			stmt = conn.prepareStatement("select r.amount,r.description,r.status,r.hasImage, r.id, r.employeeid from reimbursementrequest r inner join employee e on r.employeeid  = e.id where e.username = ? ;");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			while(rset.next()) {
				ReimbursementRequest rr = new ReimbursementRequest();
				rr.setAmount(rset.getDouble("amount"));
				rr.setDescription(rset.getString("description"));
				rr.setHasImage(rset.getBoolean("hasImage"));
				rr.setStatus(rset.getString("status"));
				rr.setId(rset.getInt("id"));
				rr.setEmployeeId(rset.getInt("employeeid"));
				rrs.add(rr);
			}
			return rrs;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DescriptionMustNotBeBlankExcpetion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidReimbursementStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionCloser.closeResource(conn);
			ConnectionCloser.closeResource(stmt);
		}
		return null;
	}

	public ArrayList<ReimbursementWrapper> getManagersPendingEmployeeReimbursementRequests(int managerid){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ArrayList<ReimbursementWrapper> rrs = new ArrayList<>();
			stmt = conn.prepareStatement("select r.amount,r.description,r.status,r.hasImage, r.id, r.employeeid, r.resolver , e.first_name as \"resolverf\", e.last_name as \"resolverl\" ,e2.first_name as \"efirst\",e2.last_name as \"elast\" from reimbursementrequest r left outer join employee e on r.resolver = e.id inner join employee e2 on r.employeeid = e2.id where e2.managerid =? and r.status='pending'");
			stmt.setInt(1, managerid);
			ResultSet rset = stmt.executeQuery();
			while(rset.next()) {
				ReimbursementWrapper rr = new ReimbursementWrapper();
				rr.setAmount(rset.getDouble("amount"));
				rr.setDescription(rset.getString("description"));
				rr.setHasImage(rset.getBoolean("hasImage"));
				rr.setReimbursementId(rset.getInt("id"));
				rr.setStatus(rset.getString("status"));
				rr.setEmployeeid(rset.getInt("employeeid"));
				rr.setResolverid(rset.getInt("resolver"));
				rr.setEmployeeFullName(rset.getString("efirst")+ " "+ rset.getString("elast"));
				rr.setResovlerFullName(rset.getString("resolverf")+ " " + rset.getString("resolverl"));
				rrs.add(rr);
			}
			return rrs;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			ConnectionCloser.closeResource(conn);
			ConnectionCloser.closeResource(stmt);
		}
		return null;
	}
	@Override
	public List<ReimbursementRequest> getPendingReimbursementRequestsByEmployee(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementRequest> getResolvedReimbursementRequestsByEmployee(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeStatus(int reimbursmentImageId, int managerId, String status) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ArrayList<ReimbursementWrapper> rrs = new ArrayList<>();
			stmt = conn.prepareStatement("update reimbursementrequest set status = ?, resolver=? where id = ?");
			stmt.setInt(3, reimbursmentImageId);
			stmt.setInt(2, managerId);
			stmt.setString(1, status);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			ConnectionCloser.closeResource(conn);
			ConnectionCloser.closeResource(stmt);
		}
		return ;
	}

}
