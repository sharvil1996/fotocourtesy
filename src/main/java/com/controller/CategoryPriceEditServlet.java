package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AdminBean;
import com.bean.CategoryPriceBean;
import com.dao.AdminDAO;
import com.dao.CategoryPriceDAO;

public class CategoryPriceEditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoryPriceId = request.getParameter("categoryPriceId");
		CategoryPriceBean categoryPriceBean = new CategoryPriceDAO().getByPK(categoryPriceId);
		if (categoryPriceBean != null) {
			request.setAttribute("categoryPriceBean", categoryPriceBean);
			request.getRequestDispatcher("categoryPriceUpdate.jsp").forward(request, response);
		} else {
			response.sendRedirect("CategoryPriceListServlet");
		}
	}

}
