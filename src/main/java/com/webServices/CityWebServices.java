package com.webServices;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.bean.CityBean;
import com.dao.CityDAO;
import com.util.GenrateMathodsUtils;

@Path("/city")
public class CityWebServices {
/*	@GET
	@Path("/cityInsert")
	@Produces("application/json")
	public Response insertCity(@Context UriInfo info) {
		String cityName = info.getQueryParameters().getFirst("cityName");
		String countryName = info.getQueryParameters().getFirst("countryName");
		CityBean bean = new CityBean();
		bean.setCityId(GenrateMathodsUtils.getRandomString(15));
		bean.setCityName(cityName);
		bean.setCountryName(countryName);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new CityDAO().insert(bean)) {
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
	@Path("/cityList")
	@Produces("application/json")
	public Response getList() {
		HashMap<String, Object> list = (HashMap<String, Object>) new CityDAO().list();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

/*	@GET
	@Path("/cityDelete")
	@Produces("application/json")
	public Response getDeletedList(@Context UriInfo info) {
		if (new CityDAO().delete(info.getQueryParameters().getFirst("cityId")))
			return Response.ok(new CityDAO().list()).header("Access-Control-Allow-Origin", "*").build();
		return Response.ok(new CityDAO().list()).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/cityUpdate")
	@Produces("application/json")
	public Response updateCity(@Context UriInfo info) {
		String cityName = info.getQueryParameters().getFirst("cityName");
		String countryName = info.getQueryParameters().getFirst("countryName");
		String cityId = info.getQueryParameters().getFirst("cityId");
		CityBean bean = new CityBean();
		bean.setCityId(cityId);
		bean.setCityName(cityName);
		bean.setCountryName(countryName);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new CityDAO().update(bean)) {
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
