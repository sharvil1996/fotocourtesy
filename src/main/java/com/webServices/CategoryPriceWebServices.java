package com.webServices;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;

import com.bean.CategoryPriceBean;
import com.dao.CategoryPriceDAO;
import com.util.GenrateMathodsUtils;

@Path("/categoryPrice")
public class CategoryPriceWebServices {

	@GET
	@Path("/categoryPriceInsert")
	@Produces("application/json")
	public Response insertCategoryPrice(@Context UriInfo info) {

		CategoryPriceBean categoryPriceBean = new CategoryPriceBean();
		String photographersId = info.getQueryParameters().getFirst("photographersId");
		String categoryId = info.getQueryParameters().getFirst("categoryId");
		String price = info.getQueryParameters().getFirst("price");

		categoryPriceBean.setCategoryId(categoryId);
		categoryPriceBean.setCategoryPriceId(GenrateMathodsUtils.getRandomString(15));
		categoryPriceBean.setPhotographersId(photographersId);
		categoryPriceBean.setPrice(price);
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (new CategoryPriceDAO().insert(categoryPriceBean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/categoryPhotographerPriceList")
	@Produces("application/json")
	public Response getPhotographerList(@Context UriInfo info) {
		HashMap<String, Object> list = (HashMap<String, Object>) new CategoryPriceDAO()
				.getList(info.getQueryParameters().getFirst("photographerId"));
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/categoryPriceList")
	@Produces("application/json")
	public Response getList() {
		HashMap<String, Object> list = (HashMap<String, Object>) new CategoryPriceDAO().list();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/categoryPriceDelete")
	@Produces("application/json")
	public Response getDeletedList(@Context UriInfo info) {
		if (new CategoryPriceDAO().delete(info.getQueryParameters().getFirst("categoryPriceId"))) {
			return Response.ok(new CategoryPriceDAO().list()).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.ok(new CategoryPriceDAO().list()).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/categoryPriceUpdate")
	@Produces("application/json")
	public Response updateCategoryPrice(@Context UriInfo info) {

		String photographersId = info.getQueryParameters().getFirst("photographersId");
		String categoryId = info.getQueryParameters().getFirst("categoryId");
		String price = info.getQueryParameters().getFirst("price");
		String categoryPriceId = info.getQueryParameters().getFirst("categoryPriceId");

		CategoryPriceBean categoryPriceBean = new CategoryPriceBean();
		categoryPriceBean.setCategoryId(categoryId);
		categoryPriceBean.setCategoryPriceId(categoryPriceId);
		categoryPriceBean.setPhotographersId(photographersId);
		categoryPriceBean.setPrice(price);

		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new CategoryPriceDAO().update(categoryPriceBean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

}
