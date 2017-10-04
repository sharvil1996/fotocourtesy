package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CountryBean;
import com.dao.CountryDAO;
import com.util.ValidationUtils;

/**
 * Servlet implementation class CountryUpdateServlet
 */
public class CountryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String countryName = request.getParameter("txtCountryName");
		String countryCode = request.getParameter("txtCountryCode");
		String countryId = request.getParameter("countryId");
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
			countryBean.setCountryId(countryId);
			countryBean.setCountryName(countryName);
			countryBean.setCountryCode(countryCode);
			request.setAttribute("countryBean", countryBean);
			request.getRequestDispatcher("countryEdit.jsp").forward(request, response);
		} else {
			countryBean.setCountryId(countryId);
			if (new CountryDAO().update(countryBean)) {
				response.sendRedirect("CountryListServlet");
			} else {
				response.sendRedirect("CountryListServlet");
			}

		}
	}

}
