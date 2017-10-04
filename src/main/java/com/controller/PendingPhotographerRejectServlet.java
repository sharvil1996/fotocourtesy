package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PhotographerDAO;


public class PendingPhotographerRejectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String photographerid = request.getParameter("photographerId");
		if (new PhotographerDAO().reject(photographerid)) {
			response.sendRedirect("RejectedPhotographerListServlet");
		} else {
			response.sendRedirect("RejectedPhotographerListServlet");
		}
	}

}
