package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;

import com.bean.CategoryPriceBean;
import com.dao.CategoryPriceDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class CategoryPriceInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryPriceBean categoryPriceBean = new CategoryPriceBean();
		String photographersId = request.getParameter("photographerId");
		String categoryId = request.getParameter("cmbCategory");
		String price = request.getParameter("txtPrice");
		request.setAttribute("photographerId", photographersId);
		categoryPriceBean.setPhotographersId(photographersId);
		boolean isError = false;
		if (categoryId.equals("0")) {
			isError = true;
			request.setAttribute("category", "* Category Selection is Required");
		} else {
			if(new CategoryPriceDAO().isExist(photographersId, categoryId))
			{
				isError = true;
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("category", "* You already Register This category Price Please Try to update it");
			}
			else
			{
			categoryPriceBean.setCategoryId(categoryId);
			request.setAttribute("categoryId", categoryId);
			}
		}
		if (ValidationUtils.isEmpty(price)) {
			isError = true;
			request.setAttribute("price", "* Price is Required");
		} else {
			categoryPriceBean.setPrice(price);
			request.setAttribute("txtPrice", price);
		}

		if (isError) {
			request.getRequestDispatcher("categoryPriceInsert.jsp").forward(request, response);
		} else {
			categoryPriceBean.setCategoryPriceId(GenrateMathodsUtils.getRandomString(15));
			if (new CategoryPriceDAO().insert(categoryPriceBean)) {
				request.getRequestDispatcher("CategoryPriceListServlet").forward(request, response);
			} else {
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}