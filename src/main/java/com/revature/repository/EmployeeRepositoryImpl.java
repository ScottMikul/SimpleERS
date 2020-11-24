package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exception.InvalidAccountTypeException;
import com.revature.exception.InvalidPasswordException;
import com.revature.exception.InvalidUsernameException;
import com.revature.exception.NameMustNotBeBlankException;
import com.revature.exception.NotValidEmailException;
import com.revature.model.Employee;
import com.revature.model.EmployeeWrapper;
import com.revature.utilities.ConnectionCloser;
import com.revature.utilities.ConnectionFactory;


public class EmployeeRepositoryImpl implements EmployeeRepository{

	@Override
	public Employee logIn(String username, String password) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			final String SQL = "select * from employee where username = ? and password = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				Employee empl;
				try {
					empl = new Employee(res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6), res.getInt(7),res.getInt(8));
					System.out.println(res.getBoolean(9));
					empl.setManager(res.getBoolean(9));
					empl.setId(res.getInt(1));
					System.out.println("logging in" + empl.getusername());
					return empl;
				} catch (InvalidUsernameException | InvalidPasswordException | NotValidEmailException
						| InvalidAccountTypeException | NameMustNotBeBlankException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
			else {
				System.out.println("couldn't find anyone with username: "+ username + " password: "+ password);
				return null;
			}




		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
						ConnectionCloser.closeResource(conn);
						ConnectionCloser.closeResource(stmt);
		}
		return null;
	}

	@Override
	public List<EmployeeWrapper> getAllEmployees() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			final String SQL = "select e.first_name as \"efn\", e.last_name as \"eln\", e.username, e.email, e2.first_name as \"mfn\" , e2.last_name as \"mln\" from employee e left join employee e2 on e.managerid = e2.id; ";
			stmt = conn.prepareStatement(SQL);
			ResultSet res = stmt.executeQuery();
			ArrayList<EmployeeWrapper> empls = new ArrayList<>();
			while(res.next()) {

				EmployeeWrapper emplwap = new EmployeeWrapper();
				emplwap.setEmployeeFullName(res.getString("efn")+ " " + res.getString("eln"));
				emplwap.setManagerFullName(res.getString("mfn")+ " " + res.getString("mln"));
				emplwap.setEmail(res.getString("email"));
				emplwap.setUsername(res.getString("username"));
				empls.add(emplwap) ;
			}
			return empls;



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
						ConnectionCloser.closeResource(conn);
						ConnectionCloser.closeResource(stmt);
		}
		return null;
	}

	@Override
	public Employee getProfileInfo(int EmployeeId) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			final String SQL = "select * from employee where id = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, EmployeeId);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				Employee empl;
				try {
					empl = new Employee(res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6), res.getInt(7),res.getInt(8));
					System.out.println(res.getBoolean(9));
					empl.setManager(res.getBoolean(9));
					System.out.println("logging in" + empl.getusername());
					return empl;
				} catch (InvalidUsernameException | InvalidPasswordException | NotValidEmailException
						| InvalidAccountTypeException | NameMustNotBeBlankException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
			else {
				return null;
			}




		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
						ConnectionCloser.closeResource(conn);
						ConnectionCloser.closeResource(stmt);
		}
		return null;
	}

	@Override
	public void updateEmployeProfileInfo(Employee empl) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("update employee set first_name = ?, last_name = ?, username = ?, password=?, email = ? where id = ? ");
			stmt.setString(1, empl.getFirstName());
			stmt.setString(2, empl.getLastName());
			stmt.setString(3, empl.getusername());
			stmt.setString(4, empl.getPassword());
			stmt.setString(5, empl.getEmail());
			stmt.setInt(6, empl.getId());
			stmt.executeUpdate();

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
	public List<Employee> getAllManagerEmployees(Employee manager) {
		// TODO Auto-generated method stub
		return null;
	}

}
