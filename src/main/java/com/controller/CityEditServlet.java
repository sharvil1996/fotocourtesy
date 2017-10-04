package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CityBean;
import com.dao.CityDAO;

/**
 * Servlet implementation class CityEditServlet
 */
public class CityEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cityid = request.getParameter("cityId");
		CityBean cityBean = new CityDAO().getByPK(cityid);
		if (cityBean != null) {
			request.setAttribute("cityBean", cityBean);
			request.getRequestDispatcher("cityEdit.jsp").forward(request, response);
		} else {
			response.sendRedirect("CityListServlet");
		}
	}

}
