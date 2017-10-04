package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CountryBean;
import com.dao.CountryDAO;

/**
 * Servlet implementation class CountryListServlet
 */
public class CountryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CountryBean> listOfCountry = new CountryDAO().getList();
		if (listOfCountry != null) {
			request.setAttribute("listOfCountry", listOfCountry);
			request.getRequestDispatcher("countryList.jsp").forward(request, response);
		}

	}

}
