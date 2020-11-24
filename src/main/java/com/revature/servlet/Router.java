package com.revature.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.revature.exception.DescriptionMustNotBeBlankExcpetion;
import com.revature.exception.InvalidAmountException;
import com.revature.exception.InvalidPasswordException;
import com.revature.exception.InvalidUsernameException;
import com.revature.exception.NameMustNotBeBlankException;
import com.revature.exception.NotValidEmailException;
import com.revature.model.Employee;
import com.revature.model.EmployeeWrapper;
import com.revature.model.ReimbursementRequest;
import com.revature.model.ReimbursementWrapper;
import com.revature.services.EmployeeServices;

/**
 * Servlet implementation class Router
 */
@MultipartConfig
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private EmployeeServices service;
    public Router() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String uri = request.getRequestURI().replace("/webapp/", "");
		Gson gson = new Gson();
		System.out.println(uri);
		service = new EmployeeServices();
		if(uri.contains("api/")) {
			System.out.println("contains api");
			if(uri.contains("/login")) {
				final String username = request.getParameter("username");
				final String password = request.getParameter("password");
				Employee current = service.login(username, password);
				
				if(current!=null) {

					System.out.println("got it login correct and forwarding");
					System.out.println("the current user id: " + current.getId());
					
					if(current.isManager()) {
						HttpSession session = request.getSession();
						session.setAttribute("user", "manager");
						session.setAttribute("userid", current.getId());
						session.setAttribute("username", current.getusername());
						response.sendRedirect("/webapp/views/manager.html");
					}
					else {
						HttpSession session = request.getSession();
						session.setAttribute("user", "employee");
						session.setAttribute("userid", current.getId());
						session.setAttribute("username", current.getusername());
						response.sendRedirect("/webapp/views/employee.html");
					}

				}
				else {
					response.sendRedirect("/webapp/");
				}
			}
			else if(uri.contains("/logout")){
				HttpSession session = request.getSession(false);
				System.out.println("made it to the logout");
				if (session != null) {
					System.out.println("invalidating session");
					session.invalidate();
				}
				
				response.sendRedirect("/webapp/");
			}
			else if(uri.contains("/info")) {
				Employee testEmployee = service.viewInfo((int) request.getSession().getAttribute("userid"));

				String result = gson.toJson(testEmployee,Employee.class);
				
				PrintWriter out = response.getWriter();
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        out.print(result);
		        out.flush();   
					
				
				
			}
			else if(uri.contains("/updateemployee")) {				
				final String firstname = request.getParameter("first");
				final String lastname = request.getParameter("last");
				final String username = request.getParameter("username");
				final String password = request.getParameter("password");
				final String email = request.getParameter("email");
				Employee emp = new Employee();
				try {
					emp.setFirstName(firstname);
					emp.setLastName(lastname);
					emp.setusername(username);
					emp.setPassword(password);
					emp.setEmail(email);
					emp.setId((int)request.getSession().getAttribute("userid"));
					System.out.println(emp.toString());
					service.setInfo(emp);
				} catch (NameMustNotBeBlankException | InvalidUsernameException | InvalidPasswordException | NotValidEmailException e) {
					// TODO Auto-generated catch block
					response.sendRedirect("/webapp/views/accountdetails.html");
				}
				response.sendRedirect("/webapp/views/accountdetails.html");
			}
			else if(uri.contains("/addrr")) {
				ReimbursementRequest rr = new ReimbursementRequest();
				final String description = request.getParameter("description");
				final double amount = Double.parseDouble(request.getParameter("amount")) ;
				
				Part content = request.getPart("image");
				try {
					InputStream is = content.getInputStream();
					if(is.available()<10) {
						rr.setImage(null);
						rr.setHasImage(false);
					}
					else {
						ByteArrayOutputStream os = new ByteArrayOutputStream();
						
						byte[] buffer = new byte[1024];
						
						while(is.read(buffer) !=-1) {
							os.write(buffer);
						}
						rr.setImage(os.toByteArray());
						rr.setHasImage(true);
					}

					rr.setEmployeeId((int)request.getSession().getAttribute("userid"));
					rr.setAmount(amount);
					
					rr.setDescription(description);
					service.AddNewReimbursementRequest(rr);
					response.sendRedirect("/webapp/views/employee.html");
				}catch(IOException ex) {
					System.out.println("couldn't uplaod the file");
					response.sendRedirect("/webapp/views/addrr");
					ex.printStackTrace();
				} catch (InvalidAmountException | DescriptionMustNotBeBlankExcpetion es) {
					// TODO Auto-generated catch block
					response.sendRedirect("/webapp/views/addrr");
					es.printStackTrace();
				}
				
			}
			else if(uri.contains("/employeerr")) {
				System.out.println("inside of /employerrr");
				ArrayList<ReimbursementRequest> rrs = (ArrayList<ReimbursementRequest>) service.viewReimbursementRequests((String) request.getSession().getAttribute("username"));
				response.setContentType("application/json");
				String rrsJson = gson.toJson(rrs);
				response.getWriter().write(rrsJson);
			}
			else if(uri.contains("image")) {
				System.out.println("contains image");
				System.out.println("uri is: " + uri);
				System.out.println(uri.replace("api/image?id=", ""));
				
				//int reimbursmentImageId = Integer.parseInt(request.getQueryString().replace(", arg1))
				System.out.println(request.getQueryString());
				int reimbursmentImageId = Integer.parseInt(request.getQueryString().replace("id=", ""));
				InputStream is = new ByteArrayInputStream(service.getImage(reimbursmentImageId));		
				response.setContentType("image/jpeg");
				OutputStream os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				while (is.read(buffer) != -1) {
					os.write(buffer);
				}
				
			}
			else if(uri.contains("allemployeerequests")) {
				System.out.println("hit allemployeerequests");
				ArrayList<ReimbursementWrapper> rrs = (ArrayList<ReimbursementWrapper>) service.getAllReimbursementRequests();
				System.out.println(rrs.size());
				response.setContentType("application/json");
				String rrsJson = gson.toJson(rrs);
				response.getWriter().write(rrsJson);
			}
			else if(uri.contains("managedemployeesrr")) {
				System.out.println("hit managedemployeesrr");
				ArrayList<ReimbursementWrapper> rrs = (ArrayList<ReimbursementWrapper>) service.managersEmployeesRequests((int)request.getSession().getAttribute("userid"));
				System.out.println(rrs.size());
				response.setContentType("application/json");
				String rrsJson = gson.toJson(rrs);
				response.getWriter().write(rrsJson);
			}
			else if(uri.contains("/approve")) {
				int reimbursmentImageId = Integer.parseInt(request.getQueryString().replace("id=", ""));
				service.approveReimbursementRequest(reimbursmentImageId,(int)request.getSession().getAttribute("userid"));
				response.sendRedirect("/webapp/views/managedemployeesrr.html");
			}
			else if(uri.contains("/deny")) {
				int reimbursmentImageId = Integer.parseInt(request.getQueryString().replace("id=", ""));
				service.denyReimbursementRequest(reimbursmentImageId,(int)request.getSession().getAttribute("userid"));
				response.sendRedirect("/webapp/views/managedemployeesrr.html");
			}
			else if(uri.contains("/roster")) {
				ArrayList<EmployeeWrapper> emps = (ArrayList<EmployeeWrapper>) service.getAllEmployees();
				String empsJson = gson.toJson(emps);
				response.setContentType("application/json");
				response.getWriter().write(empsJson);
			}

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
