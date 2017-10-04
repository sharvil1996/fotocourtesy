package com.webServices;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.bean.CategoryBean;
import com.bean.CategoryPriceBean;
import com.dao.CategoryDAO;
import com.dao.CategoryPriceDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

@Path("/category")
public class CategoryWebServices {

/*	@GET
	@Path("/categoryInsert")
	@Produces("application/json")
	public Response insertCategory(@Context UriInfo info) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String categoryName = info.getQueryParameters().getFirst("categoryName");
		if (ValidationUtils.isEmpty(categoryName)) {
			map.put("code", "400");
			map.put("status", "fail");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		CategoryBean bean = new CategoryBean();
		bean.setCategoryId(GenrateMathodsUtils.getRandomString(15));
		bean.setCategoryName(categoryName);

		if (new CategoryDAO().insert(bean)) {
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
	@Path("/categoryList")
	@Produces("application/json")
	public Response getList() {
		HashMap<String, Object> list = (HashMap<String, Object>) new CategoryDAO().getList();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

/*	@GET
	@Path("/categoryDelete")
	@Produces("application/json")
	public Response getDeletedList(@Context UriInfo info) {
		if (new CategoryDAO().delete(info.getQueryParameters().getFirst("categoryId")))
			return Response.ok(new CategoryDAO().getList()).header("Access-Control-Allow-Origin", "*").build();
		return Response.ok(new CategoryDAO().getList()).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/categoryUpdate")
	@Produces("application/json")
	public Response updateCategory(@Context UriInfo info) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String categoryName = info.getQueryParameters().getFirst("categoryName");
		String categoryId = info.getQueryParameters().getFirst("categoryId");
		if (ValidationUtils.isEmpty(categoryName) || ValidationUtils.isEmpty(categoryId)) {
			map.put("code", "400");
			map.put("status", "fail");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		CategoryBean bean = new CategoryBean();
		bean.setCategoryId(categoryId);
		bean.setCategoryName(categoryName);

		if (new CategoryDAO().update(bean)) {
			map.put("code", "201");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}
*/	
	
	@GET
	@Path("/categoryPriceInsert")
	@Produces("application/json")
	public Response insertCategoryPrice(@Context UriInfo info) {

		CategoryPriceBean bean = new CategoryPriceBean();
		String photographersId = info.getQueryParameters().getFirst("photographersId");
		String categoryId = info.getQueryParameters().getFirst("categoryId");
		String price = info.getQueryParameters().getFirst("price");
		
		
		bean.setCategoryId(categoryId);
		bean.setCategoryPriceId(GenrateMathodsUtils.getRandomString(15));
		bean.setPhotographersId(photographersId);
		bean.setPrice(price);
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (new CategoryPriceDAO().insert(bean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

}
