package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.PhotographerBean;
import com.dao.PhotographerDAO;
import com.util.ValidationUtils;


public class PhotographerLoginServlet extends HttpServlet {

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
		String photographerEmail = request.getParameter("txtPhotographerEmailId");
		String photographerPassword = request.getParameter("pwdPassword");
		request.setAttribute("photographerEmailId", "checking");

		boolean isError = false;

		if (ValidationUtils.isEmpty(photographerEmail)) {
			isError = true;
			request.setAttribute("photographerEmailId", "<font color=red>* E-MAIL is Required</font>");
		} else {
			request.setAttribute("txtPhotographerEmailId", photographerEmail);
		}

		if (ValidationUtils.isEmpty(photographerPassword)) {
			isError = true;
			request.setAttribute("password", "<font color=red>* Password is Required</font>");
		}

		if (isError) {
			request.setAttribute("photographerEmailId", "ERROR");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			PhotographerBean photographerBean = null;
			photographerBean = new PhotographerDAO().login(photographerEmail, photographerPassword);
			if (photographerBean != null) {
				HttpSession session = request.getSession();
				session.setAttribute("photographerBean", photographerBean);
				request.getRequestDispatcher("photographerIndex.jsp").forward(request, response);
			} else {
				request.setAttribute("photographerEmailId", "ERROR");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}

}
