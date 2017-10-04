package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PhotographerBean;
import com.dao.PhotographerDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class PhotographerInsertServlet extends HttpServlet {

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
		String email = request.getParameter("txtEmailId");
		String firstName = request.getParameter("txtFirstName");
		String lastName = request.getParameter("txtLastName");
		String contact1 = request.getParameter("txtContactNo1");
		String address = request.getParameter("txtAddress");
		String cityId = request.getParameter("cmbCity");
		String username=request.getParameter("txtUsername");
		
		PhotographerBean photographer = new PhotographerBean();

		boolean isError = false;

		if(cityId.equals("0")){
			request.setAttribute("cityId", "<font color=red>* CityId is Required</font>");
		}
		if (ValidationUtils.isEmpty(email)) {
			isError = true;
			request.setAttribute("emailId", "<font color=red>* E-MAIL is Required</font>");
		} else if (!ValidationUtils.isValidEmailAddress(email)) {
			isError = true;
			request.setAttribute("txtEmailId", email);
			request.setAttribute("emailId", "<font color=red>* E-MAIL is Not Proper</font>");
		} else {
			request.setAttribute("txtEmailId", email);
			photographer.setPhotographersEmailId(email);
		}


		if (ValidationUtils.isEmpty(firstName)) {
			isError = true;
			request.setAttribute("firstName", "<font color=red>* First Name is Required</font>");
		} else {
			request.setAttribute("txtFirstName", firstName);
			photographer.setPhotographersFirstName(firstName);
		}
		
		if (ValidationUtils.isEmpty(username)) {
			isError = true;
			request.setAttribute("username", "<font color=red>* User Name is Required</font>");
		} else if(new PhotographerDAO().checkUsername(username)){
			isError = true;
			request.setAttribute("txtUsername", username);
			photographer.setUsername(username);
			request.setAttribute("username", "<font color=red>* User Name is Already Exist</font>");
		}
		else{
			request.setAttribute("txtUsername", username);
			photographer.setUsername(username);
		}

		if (ValidationUtils.isEmpty(lastName)) {
			isError = true;
			request.setAttribute("lastName", "<font color=red>* Last Name is Required</font>");
		} else {
			request.setAttribute("txtLastName", lastName);
			photographer.setPhotographersLastName(lastName);
		}

		if (ValidationUtils.isEmpty(contact1)) {
			isError = true;
			request.setAttribute("contact1", "<font color=red>* Contact is Required</font>");
		}

		else {
			request.setAttribute("txtContactNo1", contact1);
			photographer.setPhotographersContact1(contact1);
		}

		if (cityId.equals("0")) {
			isError = true;
			request.setAttribute("city", "<font color=red>* City is Required</font>");
		}

		else {
			request.setAttribute("cmbCity", cityId);
			photographer.setCityId(cityId);
		}

		if (ValidationUtils.isEmpty(address)) {
			isError = true;
			request.setAttribute("address", "<font color=red>* Address is Required</font>");
		}
		else {
			request.setAttribute("txtAddress", address);
			photographer.setPhotographersAddress(address);
		}

		if (isError) {
			request.setAttribute("photographer", photographer);
			request.getRequestDispatcher("photographerInsert.jsp").forward(request, response);
		} else {
			if (new PhotographerDAO().insertPhotographer(photographer)) {
				response.sendRedirect("PendingPhotographerListServlet");
			} else {
				response.sendRedirect("PendingPhotographerListServlet");
			}

		}
	}

}