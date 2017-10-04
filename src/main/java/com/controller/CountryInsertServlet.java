package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CountryBean;
import com.dao.CountryDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

/**
 * Servlet implementation class CountryInsertServlet
 */
public class CountryInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String countryName = request.getParameter("txtCountryName");
		String countryCode = request.getParameter("txtCountryCode");
		CountryBean countryBean = new CountryBean();

		boolean isError = false;

		if (ValidationUtils.isEmpty(countryName)) {
			isError = true;
			request.setAttribute("countryName", "<font color=red>* Country Name is Required</font>");
		} else {
			request.setAttribute("txtCountryName", countryName);
			countryBean.setCountryName(countryName);
		}

		if (ValidationUtils.isEmpty(countryCode)) {
			isError = true;
			request.setAttribute("countryCode", "<font color=red>* Country Code is Required</font>");
		} else {
			request.setAttribute("txtCountryCode", countryCode);
			countryBean.setCountryCode(countryCode);
		}

		if (isError) {
			request.setAttribute("countryBean", countryBean);
			request.getRequestDispatcher("countryInsert.jsp").forward(request, response);
		} else {
			countryBean.setCountryId(GenrateMathodsUtils.getRandomString(15));
			if (new CountryDAO().insert(countryBean)) {
				response.sendRedirect("CountryListServlet");
			} else {
				response.sendRedirect("CountryListServlet");
			}

		}
	}

}