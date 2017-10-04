package com.webServices;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import com.bean.AdminBean;
import com.dao.AdminDAO;
import com.util.GenrateMathodsUtils;

@Path("/admin")
public class AdminWebServices {
/*
	@GET
	@Path("/adminInsert")
	@Produces("application/json")
	public Response insertAdmin(@Context UriInfo info) {

		String firstName = info.getQueryParameters().getFirst("firstName");
		String lastName = info.getQueryParameters().getFirst("fastName");
		String email = info.getQueryParameters().getFirst("emailId");
		String password = info.getQueryParameters().getFirst("password");
		String contact1 = info.getQueryParameters().getFirst("contactNo1");
		String contact2 = info.getQueryParameters().getFirst("contactNo2");

		AdminBean adminBean = new AdminBean();
		adminBean.setFirstName(firstName);
		adminBean.setLastName(lastName);
		adminBean.setEmailId(email);
		adminBean.setPassword(GenrateMathodsUtils.makeSHA512(password));
		adminBean.setAdminId(GenrateMathodsUtils.getRandomString(15));
		adminBean.setContact1(contact1);
		adminBean.setContact2(contact2);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new AdminDAO().insert(adminBean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/adminList")
	@Produces("application/json")
	public Response getList() {
		HashMap<String, Object> list = (HashMap<String, Object>) new AdminDAO().getList();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/adminDelete")
	@Produces("application/json")
	public Response getDeletedList(@Context UriInfo info) {
		if (new AdminDAO().delete(info.getQueryParameters().getFirst("adminId"))) {
			return Response.ok(new AdminDAO().getList()).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.ok(new AdminDAO().getList()).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/adminUpdate")
	@Produces("application/json")
	public Response updateAdmin(@Context UriInfo info) {

		String firstName = info.getQueryParameters().getFirst("firstName");
		String lastName = info.getQueryParameters().getFirst("fastName");
		String email = info.getQueryParameters().getFirst("emailId");
		String contact1 = info.getQueryParameters().getFirst("contactNo1");
		String contact2 = info.getQueryParameters().getFirst("contactNo2");
		String isAvailable = info.getQueryParameters().getFirst("isAvailable");
		String adminId = info.getQueryParameters().getFirst("adminId");
		AdminBean adminBean = new AdminBean();
		adminBean.setAdminId(adminId);
		adminBean.setFirstName(firstName);
		adminBean.setLastName(lastName);
		adminBean.setEmailId(email);
		adminBean.setContact1(contact1);
		adminBean.setContact2(contact2);
		adminBean.setIsAvailable(isAvailable);

		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new AdminDAO().update(adminBean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}
*/
}
