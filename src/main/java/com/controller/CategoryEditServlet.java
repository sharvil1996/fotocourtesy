package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CategoryBean;
import com.dao.CategoryDAO;

public class CategoryEditServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3093580174015278717L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		CategoryBean categoryBean = new CategoryDAO().getByPK(categoryId);
		if (categoryBean != null) {
			request.setAttribute("categoryBean", categoryBean);
			request.getRequestDispatcher("categoryUpdate.jsp").forward(request, response);
		} else {
			response.sendRedirect("CategoryListServlet");
		}
	}

}
