package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CityDAO;

/**
 * Servlet implementation class CityDeleteServlet
 */
public class CityDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cityid = request.getParameter("cityId");
		if (new CityDAO().delete(cityid)) {
			response.sendRedirect("CityListServlet");
		} else {
			response.sendRedirect("CityListServlet");
		}
	}

}
