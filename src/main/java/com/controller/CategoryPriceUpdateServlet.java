package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CategoryPriceBean;
import com.dao.CategoryPriceDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class CategoryPriceUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryPriceBean categoryPriceBean = new CategoryPriceBean();

		String photographersId = request.getParameter("photographerId");
		String categoryId = request.getParameter("cmbCategory");
		String price = request.getParameter("txtPrice");
		String categoryPriceId = request.getParameter("categoryPriceId");
		categoryPriceBean.setCategoryPriceId(categoryPriceId);
		request.setAttribute("photographerId", photographersId);
		categoryPriceBean.setPhotographersId(photographersId);
		boolean isError = false;
		categoryPriceBean.setCategoryId(categoryId);
		request.setAttribute("categoryId", categoryId);
		if (ValidationUtils.isEmpty(price)) {
			isError = true;
			request.setAttribute("price", "* Price is Required");
		} else {
			categoryPriceBean.setPrice(price);
			request.setAttribute("txtPrice", price);
		}
		
		if (categoryId.equals("0")) {
			isError = true;
			request.setAttribute("category", "* Category Selection is Required");
		} else {
			if(!new CategoryPriceDAO().isExist(photographersId, categoryId))
			{
				isError = true;
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("category", "* You are not Register This category Price Please Try to Register it");
			}
			else
			{
			categoryPriceBean.setCategoryId(categoryId);
			request.setAttribute("categoryId", categoryId);
			}
		}
		
		if (isError) {
			request.setAttribute("categoryPriceBean", categoryPriceBean);
			request.getRequestDispatcher("categoryPriceUpdate.jsp").forward(request, response);
		} else {
			System.out.println(photographersId+" "+categoryId);
			if (new CategoryPriceDAO().update(categoryPriceBean)) {
				System.out.println("hoiio");
				request.getRequestDispatcher("CategoryPriceListServlet").forward(request, response);
			} else {
				System.out.println("llalla");
				request.getRequestDispatcher("CategoryPriceListServlet").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
