package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PhotographerBean;
import com.dao.PhotographerDAO;

public class PhotographerEditServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String photographeriId = request.getParameter("photographerId");
		PhotographerBean photographerBean = new PhotographerDAO().getByPK(photographeriId);
		if (photographerBean != null) {
			request.setAttribute("photographerBean", photographerBean);
			request.getRequestDispatcher("photographerUpdate.jsp").forward(request, response);
		} else {
			response.sendRedirect("PhotographerListServlet");
		}
	}

}
