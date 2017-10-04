package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AlbumBean;
import com.dao.AlbumDAO;

public class AlbumListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<AlbumBean> listOfAlbum = new AlbumDAO().list();
		if (listOfAlbum != null) {
			request.setAttribute("listOfAlbum", listOfAlbum);
			request.getRequestDispatcher("albumList.jsp").forward(request, response);
		}

	}

}
