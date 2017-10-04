package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ForgotPasswordBean;
import com.bean.PhotographerBean;
import com.dao.ForgotPasswordDAO;
import com.dao.PhotographerDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailId = request.getParameter("txtEmailId");
		boolean isError = false;
		String customerId = null;
		String forgotPasswordId;
		ForgotPasswordBean forgotPasswordBean = new ForgotPasswordBean();
		System.out.println("HI" + emailId);
		if (ValidationUtils.isEmpty(emailId)) {
			isError = true;
			request.setAttribute("email", "<script>showError('emailError','Please Enter Email')</script>");
		} else {
			request.setAttribute("txtEmailId", emailId);
			customerId = new PhotographerDAO().isEmailExists(emailId);
			forgotPasswordBean.setCustomerId(customerId);
		}

		if (!ValidationUtils.isValidEmailAddress(emailId)) {
			isError = true;
			request.setAttribute("email", "<script>showError('emailError','Please Enter Email')</script>");
		}

		if (isError) {
			request.setAttribute("photographerEmailId", "ERROR");
			request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
		} else {
			System.out.println("hi");
			forgotPasswordId = GenrateMathodsUtils.getRandomString(30);
			forgotPasswordBean.setForgotPasswordId(forgotPasswordId);

			if (new ForgotPasswordDAO().checkUser(customerId)) {
				System.out.println("in if");
				if (new ForgotPasswordDAO().updateForgotPasswordId(forgotPasswordId, customerId)) {
					PhotographerBean photographerBean = new PhotographerDAO().getByPK(customerId);
					String subject = "Forgot Password";
					String msgReset = "<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
							+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='http://www.fotocourtesy.com'><img src='http://www.fotocourtesy.com/imgs/logo.jpg' style='max-height: 45px;max-width: 90%;display: inline-block;' />"
							+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
							+ "<strong style='display: block;margin-bottom: 20px;'>Hi "
							+ photographerBean.getPhotographersFirstName() + " "
							+ photographerBean.getPhotographersLastName()
							+ "</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>We got to know that you have forgotten your fotocourtesy.com Password. No worries, setting up a new password is easy. Simply click on the link below and enter a new password</p>"
							+ "<div style='text-align: center;margin-bottom: 15px;'><a href=\"http://www.fotocourtesy.com/changePassword.jsp?forgotPasswordId="
							+ forgotPasswordId + "&customerId=" + customerId
							+ "\" style='color: white;display: inline-block;background-color: #b03851;text-decoration: none;padding: 10px 20px;border-radius: 2px;'> Reset Password"
							+ "</a></div>"
							+ "<p style='margin: 0;display: block;margin-bottom: 15px;'>If you did not send this request to change your password, just ignore this email. Your fotocourtesy.com account is safe and sound.</p>"
							+ ""
							+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>FotoCourtesy</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
							+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. FotoCourtesy. All rights reserved.</p>"
							+ "</div></td</tr></tbody></table>";
					SendEmail S = new SendEmail();

					if (S.SendEmail(subject, emailId, msgReset).equals("success")) {

						request.setAttribute("msgUser",
								"succesfully send to " + "your email<br>please check your email<br>");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					} else {// For Fail
						request.setAttribute("msgUser", "failed!please try again");
						request.setAttribute("photographerEmailId", "ERROR");
						request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);

					}

				}
			}
			else if (new ForgotPasswordDAO().insert(forgotPasswordBean)) {

				PhotographerBean photographerBean = new PhotographerDAO().getByPK(customerId);
				String subject = "Forgot Password";
				String msgReset = "<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
						+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='http://www.fotocourtesy.com'><img src='http://www.fotocourtesy.com/logo.jpg' style='max-height: 45px;max-width: 90%;display: inline-block;' />"
						+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
						+ "<strong style='display: block;margin-bottom: 20px;'>Hi "
						+ photographerBean.getPhotographersFirstName() + " "
						+ photographerBean.getPhotographersLastName()
						+ "</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>We got to know that you have forgotten your fotocourtesy.com Password. No worries, setting up a new password is easy. Simply click on the link below and enter a new password</p>"
						+ "<div style='text-align: center;margin-bottom: 15px;'><a href=\"http://www.fotocourtesy.com/changePassword.jsp?forgotPasswordId="
						+ forgotPasswordId + "&customerId=" + customerId
						+ "\" style='color: white;display: inline-block;background-color: #b03851;text-decoration: none;padding: 10px 20px;border-radius: 2px;'> Reset Password"
						+ "</a></div>"
						+ "<p style='margin: 0;display: block;margin-bottom: 15px;'>If you did not send this request to change your password, just ignore this email. Your fotocourtesy.com account is safe and sound.</p>"
						+ ""
						+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>FotoCourtesy</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
						+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. FotoCourtesy. All rights reserved.</p>"
						+ "</div></td</tr></tbody></table>";

				SendEmail S = new SendEmail();

				if (S.SendEmail(subject, emailId, msgReset).equals("success")) {

					request.setAttribute("msgUser",
							"succesfully send to " + "your email<br>please check your email<br>");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {// For Fail
					request.setAttribute("msgUser", "failed!please try again");
					request.setAttribute("photographerEmailId", "ERROR");
					request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);

				}

			} else {
				request.setAttribute("msgUser", "failed!please try again");
				request.setAttribute("photographerEmailId", "ERROR");
				request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);

			}
		}

		System.out.println("Hi");
	}
}
