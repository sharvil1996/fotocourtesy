package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AlbumBean;
import com.dao.AlbumDAO;

public class AlbumEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String albumid = request.getParameter("albumId");
		AlbumBean albumBean = new AlbumDAO().getByPK(albumid);
		if (albumBean != null) {
			request.setAttribute("albumBean", albumBean);
			request.getRequestDispatcher("albumUpdate.jsp").forward(request, response);
		} else {
			response.sendRedirect("AdminListServlet");
		}
	}

}
