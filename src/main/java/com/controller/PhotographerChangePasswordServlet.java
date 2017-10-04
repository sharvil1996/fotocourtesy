package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PhotographerDAO;

public class PhotographerChangePasswordServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String photographerId = request.getParameter("pid");
		String oldPassword = request.getParameter("oldpassbox");
		String newPassword = request.getParameter("newpassbox");
		System.out.println("OLD" + oldPassword);
		if (!new PhotographerDAO().checkPassword(oldPassword, photographerId)) {
			request.getSession().setAttribute("photographerBean", new PhotographerDAO().getByPK(photographerId));
			request.setAttribute("msg", "Invalid Data");
			request.getRequestDispatcher("photographerChangePassword.jsp").forward(request, response);
		} else {
			if (new PhotographerDAO().changePassword(photographerId, newPassword)) {
				request.getSession().setAttribute("photographerBean", new PhotographerDAO().getByPK(photographerId));
				request.setAttribute("msg", "Password Changed");
				request.getRequestDispatcher("photographerIndex.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("photographerBean", new PhotographerDAO().getByPK(photographerId));
				request.setAttribute("msg", "Password Not Changed");
				request.getRequestDispatcher("photographerIndex.jsp").forward(request, response);
			}
		}

	}

}
