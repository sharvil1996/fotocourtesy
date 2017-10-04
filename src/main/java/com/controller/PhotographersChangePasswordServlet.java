package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.PhotographerBean;
import com.dao.PhotographerDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

@SuppressWarnings("serial")
public class PhotographersChangePasswordServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oldPassword = request.getParameter("pwdOldPassword");
		String newPassword = request.getParameter("pwdNewPassword");
		String confirmPassword = request.getParameter("pwdConfirmPassword");
		boolean isError = false;
		HttpSession session = request.getSession();
	//	PhotographerBean adminBean = (PhotographerBean) session.getAttribute("adminBean");
		PhotographerBean photographerBean=(PhotographerBean) session.getAttribute("photographerBean");

		//System.out.println(oldPassword);
		//System.err.println(adminBean.getFirstName());
		if (ValidationUtils.isEmpty(oldPassword)) {
			isError = true;
			request.setAttribute("oldPassword", "<font color=red>* OldPassword is Required</font>");
		} else if (!photographerBean.getPhotographersPassword().equals(GenrateMathodsUtils.makeSHA512(oldPassword))) {
			isError = true;
			request.setAttribute("oldPassword", "<font color=red>* Password is Wrong</font>");
		}

		if (ValidationUtils.isEmpty(newPassword)) {
			isError = true;
			request.setAttribute("newPassword", "<font color=red>* NewPassword is Required</font>");
		}
		if (ValidationUtils.isEmpty(confirmPassword)) {
			isError = true;
			request.setAttribute("confirmPassword", "<font color=red>* ConfirmPassword is Required</font>");
		} else if (!newPassword.equals(confirmPassword)) {
			isError = true;
			request.setAttribute("confirmPassword",
					"<font color=red>* NewPAssword & ConfirmPassword is not Same</font>");
		}

		if (isError) {
			request.getRequestDispatcher("photographerChangePassword.jsp").forward(request, response);
		} else {
			
			if (!new PhotographerDAO().changePassword(photographerBean.getPhotographersId(), GenrateMathodsUtils.makeSHA512(newPassword))) {
				session.invalidate();
				request.setAttribute("msgLogin", "Password is Changed......Try New Password to Login");
				request.getRequestDispatcher("photographerLogin.jsp").forward(request, response);
			} else {
				session.invalidate();
				request.setAttribute("msgLogin", "Some Thing Went Wrong......Your Password is Not Changed");
				request.getRequestDispatcher("photographerLogin.jsp").forward(request, response);
			}
		}
	}
}