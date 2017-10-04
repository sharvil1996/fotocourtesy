package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AdminBean;
import com.dao.AdminDAO;

public class AdminEditServlet extends HttpServlet {

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
		String adminid = request.getParameter("adminId");
		AdminBean adminBean = new AdminDAO().getByPK(adminid);
		if (adminBean != null) {
			request.setAttribute("adminBean", adminBean);
			request.getRequestDispatcher("adminUpdate.jsp").forward(request, response);
		} else {
			response.sendRedirect("AdminListServlet");
		}
	}

}
