package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AdminBean;
import com.dao.AdminDAO;
import com.util.ValidationUtils;


public class AdminLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminEmail = request.getParameter("txtAdminEmailId");
		String adminPassword = request.getParameter("pwdPassword");
		request.setAttribute("adminEmailId", "checking");
		boolean isError = false;

		if (ValidationUtils.isEmpty(adminEmail)) {
			isError = true;
			request.setAttribute("adminEmailId", "<font color=red>* E-MAIL is Required</font>");
		} else {
			request.setAttribute("txtAdminEmailId", adminEmail);
		}

		if (ValidationUtils.isEmpty(adminPassword)) {
			isError = true;
			request.setAttribute("password", "<font color=red>* Password is Required</font>");
		}

		if (isError) {
			request.setAttribute("adminEmailId", "ERROR");
			request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
		} else {
			AdminBean adminBean = null;
			adminBean = new AdminDAO().login(adminEmail, adminPassword);
			if (adminBean != null) {
				HttpSession session = request.getSession();
				session.setAttribute("adminBean", adminBean);
				request.getRequestDispatcher("adminDashBoard.jsp").forward(request, response);
			} else {
				request.setAttribute("adminEmailId", "ERROR");
				request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
			}
		}
	}

}
