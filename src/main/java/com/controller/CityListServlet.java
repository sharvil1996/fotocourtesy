package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CityBean;
import com.dao.CityDAO;

/**
 * Servlet implementation class CityListServlet
 */
public class CityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String valAjax = request.getParameter("valAjax");
		if (valAjax == null) {
			List<CityBean> listOfCity = new CityDAO().getList();
			if (listOfCity != null) {
				request.setAttribute("listOfCity", listOfCity);
				request.getRequestDispatcher("cityList.jsp").forward(request, response);
			}
		} else {
			ArrayList<CityBean> list = new CityDAO().getCityList(valAjax);
			response.getWriter()
					.write("Select City : <select><option value='0'>--Select City--</option>");
			for (int i = 0; i < list.size(); i++)
				response.getWriter().write(
						"<option value=" + list.get(i).getCityId() + ">" + list.get(i).getCityName() + "</option>");
			response.getWriter().write("</select>");
		}

	}

}
