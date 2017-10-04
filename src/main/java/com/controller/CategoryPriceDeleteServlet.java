package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CategoryPriceDAO;

public class CategoryPriceDeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryPriceId = request.getParameter("categoryPriceId");

		if (new CategoryPriceDAO().delete(categoryPriceId)) {
			request.getRequestDispatcher("CategoryPriceListServlet").forward(request, response);
		} else {
			request.getRequestDispatcher("CategoryPriceListServlet").forward(request, response);
		}
	}

}
