package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AdminBean;
import com.dao.AdminDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

@SuppressWarnings("serial")
public class AdminInsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("txtEmailId");
		String password = request.getParameter("pwdPassword");
		String firstName = request.getParameter("txtFirstName");
		String lastName = request.getParameter("txtLastName");
		String contact1 = request.getParameter("txtContactNo1");
		String contact2 = request.getParameter("txtContactNo2");
		AdminBean adminBean = new AdminBean();

		boolean isError = false;

		if (ValidationUtils.isEmpty(email)) {
			isError = true;
			request.setAttribute("emailId", "<font color=red>* E-MAIL is Required</font>");
		} else if (!ValidationUtils.isValidEmailAddress(email)) {
			isError = true;
			request.setAttribute("txtEmailId", email);
			request.setAttribute("emailId", "<font color=red>* E-MAIL is Not Proper</font>");
		} else {
			request.setAttribute("txtEmailId", email);
			adminBean.setEmailId(email);
		}

		if (ValidationUtils.isEmpty(password)) {
			isError = true;
			request.setAttribute("password", "<font color=red>* PASSWORD is Required</font>");
		} else {
			adminBean.setPassword(GenrateMathodsUtils.makeSHA512(password));
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
		}

		else {
			request.setAttribute("txtContactNo1", contact1);
			adminBean.setContact1(contact1);
		}

		if (!ValidationUtils.isEmpty(contact2)) {
			request.setAttribute("txtContactNo2", contact2);
			adminBean.setContact2(contact2);
		} else
			adminBean.setContact2(" ");

		if (isError) {
			request.setAttribute("adminBean", adminBean);
			request.getRequestDispatcher("adminInsert.jsp").forward(request, response);
		} else {
			if (new AdminDAO().insert(adminBean)) {
				response.sendRedirect("AdminListServlet");
			} else {
				response.sendRedirect("AdminListServlet");
			}

		}
	}

}
