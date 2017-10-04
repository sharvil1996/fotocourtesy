package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CountryBean;
import com.dao.CountryDAO;

/**
 * Servlet implementation class CountryEditServlet
 */
public class CountryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String countryid = request.getParameter("countryId");
		CountryBean countryBean = new CountryDAO().getByPK(countryid);
		if (countryBean != null) {
			request.setAttribute("countryBean", countryBean);
			request.getRequestDispatcher("countryEdit.jsp").forward(request, response);
		} else {
			response.sendRedirect("CountryListServlet");
		}
	}

}
