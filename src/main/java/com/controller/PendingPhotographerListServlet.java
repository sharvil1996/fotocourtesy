package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PhotographerBean;
import com.dao.PhotographerDAO;

public class PendingPhotographerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<PhotographerBean> listOfPhotographer = new PhotographerDAO().pendingList();
		if (listOfPhotographer != null) {
			request.setAttribute("title", "pending");
			request.setAttribute("listOfPhotographer", listOfPhotographer);
			request.getRequestDispatcher("photographerList.jsp").forward(request, response);
		}

	}

}
