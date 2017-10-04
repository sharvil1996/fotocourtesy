package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PhotographerBean;
import com.dao.PhotographerDAO;
import com.util.ValidationUtils;

public class PhotographerUpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("txtEmailId");
		String firstName = request.getParameter("txtFirstName");
		String lastName = request.getParameter("txtLastName");
		String username = request.getParameter("txtUsername");
		String contact1 = request.getParameter("txtContactNo1");
		String contact2 = request.getParameter("txtContactNo2");
		String description = request.getParameter("txtDescription");
		String experience = request.getParameter("txtExperience");
		String address = request.getParameter("txtAddress");
		String txtStartDate = request.getParameter("txtStartDate");
		String txtEndDate = request.getParameter("txtEndDate");
		String txtPrice = request.getParameter("txtPrice");
		String cityId = request.getParameter("cmbCity");
		String photographerId = request.getParameter("photographerId");
		String hidUsername = request.getParameter("hidUsername");
		
		PhotographerBean photographer = new PhotographerBean();
		photographer.setPhotographersId(photographerId);
		photographer.setPhotographersIsAvailable(request.getParameter("rdoIsAvailable"));

		boolean isError = false;
		
		if (ValidationUtils.isEmpty(txtPrice)) {
			isError = true;
			request.setAttribute("priceError", "<font color=red>* Price is Required</font>");
		} else {
			request.setAttribute("txtPrice", txtPrice);
			photographer.setPrice(txtPrice);
		}
		if (ValidationUtils.isEmpty(txtStartDate)) {
			isError = true;
			request.setAttribute("startDateError", "<font color=red>* Start Date  is Required</font>");
		} else {
			request.setAttribute("txtStartDate", txtStartDate);
			photographer.setStartDate(txtStartDate);
		}
		if (ValidationUtils.isEmpty(txtEndDate)) {
			isError = true;
			request.setAttribute("endDateError", "<font color=red>* End date is Required</font>");
		} else {
			request.setAttribute("txtEndDate", txtEndDate);
			photographer.setEndDate(txtEndDate);
		}
		if (ValidationUtils.isEmpty(email)) {
			isError = true;
			request.setAttribute("emailId", "<font color=red>* E-MAIL is Required</font>");
		} else if (!ValidationUtils.isValidEmailAddress(email)) {
			isError = true;
			request.setAttribute("txtEmailId", email);
			photographer.setPhotographersEmailId(email);
			request.setAttribute("emailId", "<font color=red>* E-MAIL is Not Proper</font>");
		} else {
			request.setAttribute("txtAdminEmail", email);
			photographer.setPhotographersEmailId(email);
		}

		if (ValidationUtils.isEmpty(username)) {
			isError = true;
			request.setAttribute("username", "<font color=red>* User Name is Required</font>");
		} else if (new PhotographerDAO().checkUsername(username) && !username.equalsIgnoreCase(hidUsername)) {
			isError = true;
			request.setAttribute("txtUsername", username);
			photographer.setUsername(username);
			request.setAttribute("username", "<font color=red>* User Name is Already Exist</font>");
		} else {
			request.setAttribute("txtUsername", username);
			photographer.setUsername(username);
		}

		if (ValidationUtils.isEmpty(firstName)) {
			isError = true;
			request.setAttribute("firstName", "<font color=red>* First Name is Required</font>");
		} else {
			request.setAttribute("txtFirstName", firstName);
			photographer.setPhotographersFirstName(firstName);
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
		if (ValidationUtils.isEmpty(experience)) {
			isError = true;
			request.setAttribute("experience", "<font color=red>* Experience is Required</font>");
		} else {
			request.setAttribute("txtExperience", experience);
			photographer.setPhotographersExperience(experience);
		}
		if (ValidationUtils.isEmpty(description)) {
			isError = true;
			request.setAttribute("description", "<font color=red>* Description is Required</font>");
		}

		else {
			request.setAttribute("txtDescription", description);
			photographer.setPhotographersDescription(description);
		}

		if (!ValidationUtils.isEmpty(contact2)) {
			request.setAttribute("txtContactNo2", contact2);
			photographer.setPhotographersContact2(contact2);
		} else
			photographer.setPhotographersContact2(" ");

		if (isError) {
			request.setAttribute("photographerBean", photographer);
			request.getRequestDispatcher("photographerUpdate.jsp").forward(request, response);
		} else {
			if (new PhotographerDAO().update(photographer)) {
				response.sendRedirect("PhotographerListServlet");
			} else {
				response.sendRedirect("PhotographerListServlet");
			}

		}
	}

}