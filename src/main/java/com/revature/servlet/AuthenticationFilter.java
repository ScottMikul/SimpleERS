package com.revature.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//Get HttpSession information because we are checking for
		//the existence of a session!
		
		HttpSession session = req.getSession(false);
		System.out.println(session);
		if(session == null || session.getAttribute("user")==null) {
			System.out.println("the session is null so we are redirecting");
			res.sendRedirect("/webapp/");
			return;
		}
		

		// pass the request along the filter chain
		else {
			String user =  (String) session.getAttribute("user");
			if(req.getRequestURI().contains("manager") && user.equals("employee")) {
				res.sendRedirect("/webapp/views/employee.html");
				return;
			}
			else if(req.getRequestURI().contains("employee.html") && user.equals("manager")) {
				res.sendRedirect("/webapp/views/manager.html");
				return;
			}
			System.out.println("there was a session so we are passing the request along");

			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			res.setDateHeader("Expires", 0); // Proxies.

	        chain.doFilter(req, res);
		}
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
