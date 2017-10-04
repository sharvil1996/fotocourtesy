package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PhotographerBean;
import com.dao.PhotographerDAO;

public class PhotographerDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String photographerid = request.getParameter("photographerId");
		PhotographerBean photographer = new PhotographerDAO().photographerDetails(photographerid);
			request.setAttribute("photographer", photographer);
			request.getRequestDispatcher("photographerDetails.jsp").forward(request, response);
	}

}
