package com.webServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.bean.AdminBean;
import com.bean.CityBean;
import com.bean.PhotographerBean;
import com.controller.SendEmail;
import com.dao.AdminDAO;
import com.dao.CityDAO;
import com.dao.PhotographerDAO;
import com.util.GenrateMathodsUtils;

@Path("/photographer")
public class PhotographerWebServices {

	@GET
	@Path("/photographerInsert")
	@Produces("application/json")
	public Response insertPhotographer(@Context UriInfo info) {

		String email = info.getQueryParameters().getFirst("emailId");
		String password = info.getQueryParameters().getFirst("password");
		String firstName = info.getQueryParameters().getFirst("firstName");
		String lastName = info.getQueryParameters().getFirst("lastName");
		String userName = info.getQueryParameters().getFirst("userName");
		String contact1 = info.getQueryParameters().getFirst("contactNo1");
		String contact2 = info.getQueryParameters().getFirst("contactNo2");
		String description = info.getQueryParameters().getFirst("description");
		String experience = info.getQueryParameters().getFirst("experience");
		String address = info.getQueryParameters().getFirst("address");
		String cityName = info.getQueryParameters().getFirst("cityName");

		PhotographerBean photographerBean = new PhotographerBean();
		photographerBean.setPhotographersFirstName(firstName);
		photographerBean.setPhotographersLastName(lastName);
		photographerBean.setPhotographersEmailId(email);
		photographerBean.setPhotographersPassword(GenrateMathodsUtils.makeSHA512(password));
		photographerBean.setPhotographersId(GenrateMathodsUtils.getRandomString(15));
		photographerBean.setPhotographersContact1(contact1);
		photographerBean.setPhotographersContact2(contact2);
		photographerBean.setCityId(cityName);
		photographerBean.setPhotographersDescription(description);
		photographerBean.setPhotographersExperience(experience);
		photographerBean.setPhotographersAddress(address);
		photographerBean.setUsername(userName);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new PhotographerDAO().insert(photographerBean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/photographerInsertDetails")
	@Produces("application/json")
	public Response insertSomePhotographer(@Context UriInfo info) {

		String email = info.getQueryParameters().getFirst("emailId");
		String firstName = info.getQueryParameters().getFirst("firstName");
		String lastName = info.getQueryParameters().getFirst("lastName");
		String userName = info.getQueryParameters().getFirst("userName");
		String contact1 = info.getQueryParameters().getFirst("contactNo1");
		String address = info.getQueryParameters().getFirst("address");
		String cityName = info.getQueryParameters().getFirst("cityId");

		PhotographerBean photographerBean = new PhotographerBean();
		photographerBean.setPhotographersFirstName(firstName);
		photographerBean.setPhotographersLastName(lastName);
		photographerBean.setPhotographersEmailId(email);
		photographerBean.setPhotographersId(GenrateMathodsUtils.getRandomString(15));
		photographerBean.setPhotographersContact1(contact1);
		photographerBean.setPhotographersAddress(address);
		photographerBean.setCityId(cityName);
		photographerBean.setUsername(userName);
		CityBean bean = new CityDAO().getByPK(cityName);
		photographerBean.setCityName(bean.getCityName());
		photographerBean.setCountryName(bean.getCountryName());
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new PhotographerDAO().insertPhotographer(photographerBean)) {

			SendEmail S = new SendEmail();
			String msgPhotographer = "<table style='border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;max-width:800px;background-color: #fff;' cellpadding='0' cellspacing='0' role='presentation'><tbody><tr><td>"
					+ "<div style='max-width: 800px;text-align: center;padding: 10px 0;'><a href='http://www.fotocourtesy.com'><img src='http://www.fotocourtesy.com/imgs/logo.jpg' style='max-height: 45px;max-width: 90%;display: inline-block;' />"
					+ "</a></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div><div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 1em;line-height: 1.5;font-family: sans-serif;'>"
					+ "<strong style='display: block;margin-bottom: 20px;'>Hi "
					+ photographerBean.getPhotographersFirstName() + " " + photographerBean.getPhotographersLastName()
					+ ",</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>After verifying your profile & payment details,You will be notify by your password <b></u>"
					+ "</u></b>.</p>"
					+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>" + "</p>"
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
							+ "<strong style='display: block;margin-bottom: 20px;'>Hi " + a.getFirstName() + " "
							+ a.getLastName()
							+ ",</strong><p style='margin: 0;display: block;margin-bottom: 15px;'>New Photographer Request Arrived . Photographer's details are given below.</p>"
							+ "<p style='margin: 0;display: block;margin-bottom: 20px;'>Name : "
							+ photographerBean.getPhotographersFirstName() + " "
							+ photographerBean.getPhotographersLastName() + "<br>Email Id : "
							+ photographerBean.getPhotographersEmailId() + "<br>Contact No : "
							+ photographerBean.getPhotographersContact1() + "<br>City : "
							+ photographerBean.getCityName() + "<br>Country : " + photographerBean.getCountryName()
							+ "</p>"
							+ "<p style='margin: 0;'>Regards,</p><strong style='display: block;'>FotoCourtesy</strong></div><div style='background-color: #b03851;height: 4px;width: 100%;'></div>"
							+ "<div style='max-width: 600px;margin: 0 auto;padding:25px 15px;text-align: left;color: #8e959c;font-size: 0.8em;line-height: 1.5;font-family: sans-serif;'><p style='margin: 0;display: block;'>Copyright &#169; 2017. FotoCourtesy. All rights reserved.</p>"
							+ "</div></td</tr></tbody></table>";

 
					if (S.SendEmail("New Photographer Request", a.getEmailId(), details).equals("success")) {

					} else {// For Fail

					}
				}
			}

			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/photographerList")
	@Produces("application/json")
	public Response getList() {
		HashMap<String, Object> list = (HashMap<String, Object>) new PhotographerDAO().getList();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/photographerDelete")
	@Produces("application/json")
	public Response getDeletedList(@Context UriInfo info) {
		if (new PhotographerDAO().delete(info.getQueryParameters().getFirst("photographerId")))
			return Response.ok(new PhotographerDAO().getList()).header("Access-Control-Allow-Origin", "*").build();
		return Response.ok(new PhotographerDAO().getList()).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/photographerUpdate")
	@Produces("application/json")
	public Response updatePhotographer(@Context UriInfo info) {

		String email = info.getQueryParameters().getFirst("emailId");
		String firstName = info.getQueryParameters().getFirst("firstName");
		String lastName = info.getQueryParameters().getFirst("lastName");
		String userName = info.getQueryParameters().getFirst("userName");
		String contact1 = info.getQueryParameters().getFirst("contactNo1");
		String contact2 = info.getQueryParameters().getFirst("contactNo2");
		String description = info.getQueryParameters().getFirst("description");
		String experience = info.getQueryParameters().getFirst("experience");
		String address = info.getQueryParameters().getFirst("address");
		String cityName = info.getQueryParameters().getFirst("cityName");
		String price = info.getQueryParameters().getFirst("price");
		String photographerId = info.getQueryParameters().getFirst("photographerId");

		PhotographerBean photographerBean = new PhotographerBean();
		photographerBean.setPhotographersFirstName(firstName);
		photographerBean.setPhotographersLastName(lastName);
		photographerBean.setPhotographersEmailId(email);
		photographerBean.setPhotographersContact1(contact1);
		photographerBean.setPhotographersContact2(contact2);
		photographerBean.setUsername(userName);
		photographerBean.setCityId(cityName);
		photographerBean.setPhotographersDescription(description);
		photographerBean.setPhotographersExperience(experience);
		photographerBean.setPhotographersAddress(address);
		photographerBean.setPhotographersId(photographerId);
		photographerBean.setPrice(price);

		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new PhotographerDAO().update(photographerBean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/photographerCityList")
	@Produces("application/json")
	public Response getListByCityId(@QueryParam(value = "cityId") String cityId) {
		HashMap<String, Object> list = (HashMap<String, Object>) new PhotographerDAO().getByCityID(cityId);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/photographerUserName")
	@Produces("application/json")
	public Response getListByUserName(@QueryParam(value = "userName") String userName) {
		HashMap<String, Object> list = (HashMap<String, Object>) new PhotographerDAO().getByUsername(userName);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/photographerEmailId")
	@Produces("application/json")
	public Response getListByEmailId(@QueryParam(value = "emailId") String emailId) {
		HashMap<String, Object> list = (HashMap<String, Object>) new PhotographerDAO().getByEmailId(emailId);

		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/photographerView")
	@Produces("application/json")
	public Response updatePhotographersView(@Context UriInfo info) {

		String field = info.getQueryParameters().getFirst("field");
		String photographerId = info.getQueryParameters().getFirst("photographerId");

		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new PhotographerDAO().addView(photographerId, field)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

}
