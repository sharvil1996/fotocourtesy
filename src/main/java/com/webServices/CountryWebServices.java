package com.webServices;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;

import com.bean.CountryBean;
import com.dao.CountryDAO;
import com.util.GenrateMathodsUtils;

@Path("/country")
public class CountryWebServices {

/*	@GET
	@Path("/countryInsert")
	@Produces("application/json")
	public Response insertCountry(@Context UriInfo info) {
		String countryName = info.getQueryParameters().getFirst("countryName");
		String countryCode = info.getQueryParameters().getFirst("countryCode");
		CountryBean bean = new CountryBean();
		bean.setCountryId(GenrateMathodsUtils.getRandomString(15));
		bean.setCountryName(countryName);
		bean.setCountryCode(countryCode);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new CountryDAO().insert(bean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

*/
	@GET
	@Path("/countryList")
	@Produces("application/json")
	public Response getList() {
		HashMap<String, Object> list = (HashMap<String, Object>) new CountryDAO().list();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	/*@GET
	@Path("/countryDelete")
	@Produces("application/json")
	public Response getDeletedList(@Context UriInfo info) {
		if (new CountryDAO().delete(info.getQueryParameters().getFirst("countryId"))) {
			return Response.ok(new CountryDAO().list()).header("Access-Control-Allow-Origin", "*").build();

		}

		return Response.ok(new CountryDAO().list()).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/countryUpdate")
	@Produces("application/json")
	public Response updateCountry(@Context UriInfo info) {
		String countryName = info.getQueryParameters().getFirst("countryName");
		String countryCode = info.getQueryParameters().getFirst("countryCode");
		String countryId = info.getQueryParameters().getFirst("countryId");
		CountryBean bean = new CountryBean();
		bean.setCountryId(countryId);
		bean.setCountryName(countryName);
		bean.setCountryCode(countryCode);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new CountryDAO().update(bean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}
*/
	@GET
	@Path("/countryList1")
	@Produces("application/json")
	public Response updateCountry1(@Context UriInfo info) {
		HashMap<String, Object> list = (HashMap<String, Object>) new CountryDAO().list();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();

	}

}
