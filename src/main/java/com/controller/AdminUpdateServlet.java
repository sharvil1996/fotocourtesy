package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AdminBean;
import com.dao.AdminDAO;
import com.util.ValidationUtils;

public class AdminUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("txtEmailId");
		String firstName = request.getParameter("txtFirstName");
		String lastName = request.getParameter("txtLastName");
		String contact1 = request.getParameter("txtContactNo1");
		String contact2 = request.getParameter("txtContactNo2");
		String isAvailable = request.getParameter("rdoIsAvailable");
		AdminBean adminBean = new AdminBean();
		adminBean.setAdminId(request.getParameter("adminId"));

		boolean isError = false;

		if (ValidationUtils.isEmpty(email)) {
			isError = true;
			request.setAttribute("emailId", "<font color=red>* E-MAIL is Required</font>");
		} else if (!ValidationUtils.isValidEmailAddress(email)) {
			isError = true;
			request.setAttribute("txtEmailId", email);
			adminBean.setEmailId(email);
			request.setAttribute("emailId", "<font color=red>* E-MAIL is Not Proper</font>");
		} else {
			request.setAttribute("txtAdminEmail", email);
			adminBean.setEmailId(email);
		}
		if (ValidationUtils.isEmpty(firstName)) {
			isError = true;
			request.setAttribute("firstName", "<font color=red>* First Name is Required</font>");
		} else {
			request.setAttribute("txtFirstName", firstName);
			adminBean.setFirstName(firstName);
		}

		if (ValidationUtils.isEmpty(lastName)) {
			isError = true;
			request.setAttribute("lastName", "<font color=red>* Last Name is Required</font>");
		} else {
			request.setAttribute("txtLastName", lastName);
			adminBean.setLastName(lastName);
		}

		if (ValidationUtils.isEmpty(contact1)) {
			isError = true;
			request.setAttribute("contact1", "<font color=red>* Contact is Required</font>");
		} else {
			request.setAttribute("txtContactNo1", contact1);
			adminBean.setContact1(contact1);
		}

		if (!ValidationUtils.isEmpty(contact2)) {
			request.setAttribute("txtContactNo2", contact2);
			adminBean.setContact2(contact2);
		} else
			adminBean.setContact2(" ");

		if (isError) {
			adminBean.setEmailId(email);
			adminBean.setFirstName(firstName);
			adminBean.setLastName(lastName);
			adminBean.setContact1(contact1);
			adminBean.setContact2(contact2);
			adminBean.setIsAvailable(isAvailable);
			request.setAttribute("adminBean", adminBean);
			request.getRequestDispatcher("adminUpdate.jsp").forward(request, response);
		} else {
			adminBean.setIsAvailable(isAvailable);
			if (new AdminDAO().update(adminBean)) {
				response.sendRedirect("AdminListServlet");
			} else {
				response.sendRedirect("AdminListServlet");
			}

		}
	}

}