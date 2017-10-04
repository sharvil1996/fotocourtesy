package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CategoryBean;
import com.dao.CategoryDAO;
import com.util.ValidationUtils;

public class CategoryInsertServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String category = request.getParameter("txtCategory");
		CategoryBean categoryBean = new CategoryBean();
		boolean isError = false;

		if (ValidationUtils.isEmpty(category)) {
			isError = true;
			request.setAttribute("category", "<font color=red>* Category is Required</font>");
		} else {
			if (!new CategoryDAO().alreadyExist(category)) {
				request.setAttribute("txtCategory", category);
				categoryBean.setCategoryName(category);
			} else {
				isError = true;
				categoryBean.setCategoryName(category);
				request.setAttribute("txtCategory", category);
				request.setAttribute("category", "<font color=red>* Category Already Exist</font>");
			}
		}
		if (isError) {
			request.setAttribute("categoryBean", categoryBean);
			request.getRequestDispatcher("categoryInsert.jsp").forward(request, response);
		} else {
			if (new CategoryDAO().insert(categoryBean)) {
				response.sendRedirect("CategoryListServlet");
			} else {
				response.sendRedirect("CategoryListServlet");
			}

		}
	}

}
