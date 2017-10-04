package com.webServices;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;

import com.bean.AlbumBean;
import com.dao.AlbumDAO;
import com.util.GenrateMathodsUtils;

@Path("/album")
public class AlbumWebServices {
	@GET
	@Path("/albumInsert")
	@Produces("application/json")
	public Response insertAlbum(@Context UriInfo info) {

		String albumName = info.getQueryParameters().getFirst("albumName");
		String photographerId = info.getQueryParameters().getFirst("photographerId");
		String albumDescription = info.getQueryParameters().getFirst("albumDescription");
		String albumLink = info.getQueryParameters().getFirst("albumLink");

		AlbumBean albumBean = new AlbumBean();

		albumBean.setAlbumId(GenrateMathodsUtils.getRandomString(15));
		albumBean.setPhotographerId(photographerId);
		albumBean.setAlbumName(albumName);
		albumBean.setAlbumDescription(albumDescription);
		albumBean.setAlbumLink(albumLink);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new AlbumDAO().isAlbumAvailable(albumBean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/albumList")
	@Produces("application/json")
	public Response getList() {
		HashMap<String, Object> list = (HashMap<String, Object>) new AlbumDAO().getList();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/albumDelete")
	@Produces("application/json")
	public Response getDeletedList(@Context UriInfo info) {
		if (new AlbumDAO().delete(info.getQueryParameters().getFirst("albumId"))) {
			return Response.ok(new AlbumDAO().getList()).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.ok(new AlbumDAO().getList()).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/albumPhotographerList")
	@Produces("application/json")
	public Response getAlbumPhotograoherList(@Context UriInfo info) {
		HashMap<String, Object> list = (HashMap<String, Object>) new AlbumDAO().getAlbumPhotograoherList(info.getQueryParameters().getFirst("photographerId"));
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("/albumUpdate")
	@Produces("application/json")
	public Response updateAlbum(@Context UriInfo info) {

		String albumName = info.getQueryParameters().getFirst("albumName");
		String photographerId = info.getQueryParameters().getFirst("photographerId");
		String albumDescription = info.getQueryParameters().getFirst("albumDescription");
		String albumLink = info.getQueryParameters().getFirst("albumLink");
		String albumId = info.getQueryParameters().getFirst("albumId");

		AlbumBean albumBean = new AlbumBean();

		albumBean.setAlbumId(albumId);
		albumBean.setPhotographerId(photographerId);
		albumBean.setAlbumName(albumName);
		albumBean.setAlbumDescription(albumDescription);
		albumBean.setAlbumLink(albumLink);

		HashMap<String, Object> map = new HashMap<String, Object>();
		if (new AlbumDAO().update(albumBean)) {
			map.put("code", "200");
			map.put("status", "success");
			return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
		}
		map.put("code", "404");
		map.put("status", "fail");
		return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
	}

}
