package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CategoryPriceBean;
import com.dao.CategoryPriceDAO;

public class CategoryPriceListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CategoryPriceBean> listOfCategoryPrice = new CategoryPriceDAO().getCategoryPriceList();
		if (listOfCategoryPrice != null) {
			request.setAttribute("listOfCategoryPrice", listOfCategoryPrice);
		}
		request.getRequestDispatcher("catgoryPriceList.jsp").forward(request, response);

	}

}
