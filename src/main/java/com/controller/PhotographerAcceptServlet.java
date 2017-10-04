package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AdminBean;
import com.bean.PhotographerBean;
import com.dao.AdminDAO;
import com.dao.PhotographerDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class PhotographerAcceptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String photographerId = request.getParameter("photographerId");
		String txtPrice = request.getParameter("txtPrice");
		String txtStartDate = request.getParameter("txtStartDate");
		String txtEndDate = request.getParameter("txtEndDate");
		boolean isError = false;

		if (ValidationUtils.isEmpty(txtPrice)) {
			isError = true;
			request.setAttribute("price", "<font color=red>* Price is Required</font>");
		} else {
			request.setAttribute("txtPrice", txtPrice);
		}
		if (ValidationUtils.isEmpty(txtStartDate)) {
			isError = true;
			request.setAttribute("startDate", "<font color=red>* Start Date  is Required</font>");
		} else {
			request.setAttribute("txtStartDate", txtStartDate);
		}
		if (ValidationUtils.isEmpty(txtEndDate)) {
			isError = true;
			request.setAttribute("endDate", "<font color=red>* End date is Required</font>");
		} else {
			request.setAttribute("txtEndDate", txtEndDate);
		}
		if (isError) {
			request.setAttribute("photographerId", photographerId);
			request.getRequestDispatcher("photographerAccept.jsp").forward(request, response);

		} else {

			String tempPassword = GenrateMathodsUtils.getRandomString(10);
			if (!new PhotographerDAO().acceptPhotographer(photographerId, txtPrice,
					GenrateMathodsUtils.convertDateSQL(txtStartDate), GenrateMathodsUtils.convertDateSQL(txtEndDate),
					tempPassword)) {

				SendEmail S = new SendEmail();
				PhotographerBean photographerBean = new PhotographerDAO().getByPK(photographerId);

				if (S.SendEmail("New Account on FotoCourtsy", photographerBean.getPhotographersEmailId(), tempPassword)
						.equals("success")) {
					System.out.println("sss");
					/*
					 * request.setAttribute("msgUser", "succesfully send to " +
					 * "your email<br>please check your email<br>");
					 * request.getRequestDispatcher("login.jsp").forward(
					 * request, response);
					 */
				} else {// For Fail
					System.out.println("Not");
					/*
					 * request.setAttribute("msgUser",
					 * "failed!please try again");
					 * request.getRequestDispatcher("forgotPassword.jsp").
					 * forward(request, response);
					 */

				}
				response.sendRedirect("PendingPhotographerListServlet");
			} else {
				SendEmail S = new SendEmail();
				PhotographerBean photographerBean = new PhotographerDAO().getByPK(photographerId);
				String msgPhotographer="<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
						+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='http://www.fotocourtesy.com'><img src='http://www.fotocourtesy.com/imgs/logo.jpg' style='max-height: 45px;max-width: 90%;display: inline-block;' />"
						+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
						+ "<strong style='display: block;margin-bottom: 20px;'>Hi "+ photographerBean.getPhotographersFirstName() + " "
						+ photographerBean.getPhotographersLastName() +",</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>We are happy to inform you that now you are joined our website. Your password is <b></u>"+tempPassword+ "</u></b>.<br /> Now you can login in our website from here http://www.fotocourtesy.com/login.jsp</p>"
						+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>"
						+ "</p>"
						+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>FotoCourtesy</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
						+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. FotoCourtesy. All rights reserved.</p>"
						+ "</div></td</tr></tbody></table>";
				if (S.SendEmail("Account Activation", photographerBean.getPhotographersEmailId(), msgPhotographer)
						.equals("success")) {
					ArrayList<AdminBean> list = new AdminDAO().list();
					for (AdminBean a : list) {

						String details = "<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
								+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='http://www.fotocourtesy.com'><img src='http://www.fotocourtesy.com/imgs/logo.jpg' style='max-height: 45px;max-width: 90%;display: inline-block;' />"
								+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
								+ "<strong style='display: block;margin-bottom: 20px;'>Hi "+a.getFirstName() + " "+a.getLastName()+",</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>We are happy to inform you that new photographer joined our website. Photographer's details are given below.</p>"
								+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>Name : "
								+ photographerBean.getPhotographersFirstName() + " "
								+ photographerBean.getPhotographersLastName() + "<br>Email Id : "
								+ photographerBean.getPhotographersEmailId() + "<br>Price : " + txtPrice
								+ "<br>Start Date : " + txtStartDate + "<br>End Date : " + txtEndDate
								+ "<br>Contact No : " + photographerBean.getPhotographersContact1() + "<br>City : "
								+ photographerBean.getCityName() + "<br>Country : " + photographerBean.getCountryName()
								+ "</p>"
								+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>FotoCourtesy</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
								+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. FotoCourtesy. All rights reserved.</p>"
								+ "</div></td</tr></tbody></table>";

						/*
						 * String
						 * details="Name&emsp;:&emsp;&emsp;"+photographerBean.
						 * getPhotographersFirstName()
						 * +"-"+photographerBean.getPhotographersLastName() +
						 * "<br>"; details+="EmailID&emsp;:&emsp;&emsp;"+
						 * photographerBean.getPhotographersEmailId()+ "<br>";
						 * details+="Price&emsp;:&emsp;&emsp;"+txtPrice +
						 * "<br>";
						 * details+="StartDate&emsp;:&emsp;&emsp;"+txtStartDate+
						 * "<br>";
						 * details+="EndDate&emsp;:&emsp;&emsp;"+txtEndDate +
						 * "<br>";
						 * details+="Contact No.:&emsp;&emsp;"+photographerBean.
						 * getPhotographersContact1() + "<br>";
						 * details+="City:"+photographerBean.getCityName() +
						 * "<br>";
						 * details+="Country:"+photographerBean.getCountryName()
						 * + "<br>";
						 */

						/*if (S.SendEmail("New Photographer Joined", "avnishthakkar14@gmail.com", details).equals("success")) {

						} else {// For Fail

						}*/
						
						if (S.SendEmail("New Photographer Joined", a.getEmailId(), details).equals("success")) {

						} else {// For Fail

						}
					}

				} else {// For Fail
					System.out.println("Not");
					/*
					 * request.setAttribute("msgUser",
					 * "failed!please try again");
					 * request.getRequestDispatcher("forgotPassword.jsp").
					 * forward(request, response);
					 */

				}
				response.sendRedirect("PhotographerListServlet");
			}
		}
	}

}
