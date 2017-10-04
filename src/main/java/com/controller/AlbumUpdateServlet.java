package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AlbumBean;
import com.dao.AlbumDAO;
import com.util.ValidationUtils;

public class AlbumUpdateServlet extends HttpServlet {

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
		String albumName = request.getParameter("txtAlbumName");
		String photographerId = request.getParameter("cmbPhotographer");
		String albumDescription = request.getParameter("txtAlbumDescription");
		String albumLink = request.getParameter("txtAlbumLink");
		AlbumBean albumBean = new AlbumBean();
		albumBean.setAlbumId(request.getParameter("albumId"));
		boolean isError = false;

		if (ValidationUtils.isEmpty(albumName)) {
			isError = true;
			request.setAttribute("albumName", "<font color=red>* Album Name is Required</font>");
		} else {
			request.setAttribute("txtAlbumName", albumName);
			albumBean.setAlbumName(albumName);
		}

		if (photographerId.equals("0")) {
			isError = true;
			request.setAttribute("photographerId", "<font color=red>* Photographer is Required</font>");
		} else {
			albumBean.setPhotographerId(photographerId);
			request.setAttribute("photographer", photographerId);
			albumBean.setPhotographerId(photographerId);
		}

		if (ValidationUtils.isEmpty(albumDescription)) {
			isError = true;
			request.setAttribute("albumDescription", "<font color=red>* Description is Required</font>");
		} else {
			request.setAttribute("txtAlbumDescription", albumDescription);
			albumBean.setAlbumDescription(albumDescription);
		}

		if (ValidationUtils.isEmpty(albumLink)) {
			isError = true;
			request.setAttribute("albumLink", "<font color=red>* Album Link is Required</font>");
		} else {
			request.setAttribute("txtAlbumLink", albumLink);
			albumBean.setAlbumLink(albumLink);
		}

		if (isError) {
			request.setAttribute("albumBean", albumBean);
			request.getRequestDispatcher("albumUpdate.jsp").forward(request, response);
		} else {
			if (new AlbumDAO().update(albumBean)) {
				response.sendRedirect("AlbumListServlet");
			} else {
				response.sendRedirect("AlbumListServlet");
			}

		}
	}

}
