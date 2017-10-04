package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CityBean;
import com.dao.CityDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

/**
 * Servlet implementation class CityInsertServlet
 */
public class CityInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cityName = request.getParameter("txtCityName");
		String countryName = request.getParameter("selCountryName");
		CityBean cityBean = new CityBean();

		boolean isError = false;

		if (ValidationUtils.isEmpty(cityName)) {
			isError = true;
			request.setAttribute("cityName", "<font color=red>* City Name is Required</font>");
		} else {
			request.setAttribute("txtCityName", cityName);
			cityBean.setCityName(cityName);
		}

		if (countryName.equals("0")) {
			isError = true;
			request.setAttribute("countryName", "<font color=red>* Country Name is Required</font>");
		} else {
			request.setAttribute("selCountryName", countryName);
			cityBean.setCountryName(countryName);
		}

		if (isError) {
			request.setAttribute("txtCityName", cityName);
			request.setAttribute("selCountryName", countryName);
			request.setAttribute("cityBean", cityBean);
			request.getRequestDispatcher("cityInsert.jsp").forward(request, response);
		} else {
			cityBean.setCityId(GenrateMathodsUtils.getRandomString(15));
			if (new CityDAO().insert(cityBean)) {
				response.sendRedirect("CityListServlet");
			} else {
				response.sendRedirect("CityListServlet");
			}

		}
	}

}