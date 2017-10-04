package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bean.PhotographerBean;
import com.util.DBConnection;
import com.util.GenrateMathodsUtils;

public class PhotographerDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public boolean insertPhotographer(PhotographerBean photographersBean) {

		String sql = "insert into photographers"
				+ "(photographersId,photographersFirstname,photographersLastname,photographersAddress,"
				+ "photographersEmailId,photographersContactNumber1,cityId,username,photographersPassword,photographersExperience,photographersDescription,price) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
				pstmt.setString(2, photographersBean.getPhotographersFirstName());
				pstmt.setString(3, photographersBean.getPhotographersLastName());
				pstmt.setString(4, photographersBean.getPhotographersAddress());
				pstmt.setString(5, photographersBean.getPhotographersEmailId());
				pstmt.setString(6, photographersBean.getPhotographersContact1());
				pstmt.setString(7, photographersBean.getCityId());
				pstmt.setString(8, photographersBean.getUsername());
				pstmt.setString(9, "NULL");
				pstmt.setString(10, "0");
				pstmt.setString(11, "NULL");
				pstmt.setString(12, "NULL");
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return false;
	}

	public boolean insert(PhotographerBean photographersBean) {

		String sql = "insert into photographers(photographersId,photographersFirstname,photographersLastname,photographersEmailId,photographersContactNumber1,photographersContactNumber2,photographerspassword,photographersAddress,photographersExperience,photographersDescription,cityId,username) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
				pstmt.setString(2, photographersBean.getPhotographersFirstName());
				pstmt.setString(3, photographersBean.getPhotographersLastName());
				pstmt.setString(4, photographersBean.getPhotographersEmailId());
				pstmt.setString(5, photographersBean.getPhotographersContact1());
				pstmt.setString(6, photographersBean.getPhotographersContact2());
				pstmt.setString(7, GenrateMathodsUtils.makeSHA512(photographersBean.getPhotographersPassword()));
				pstmt.setString(8, photographersBean.getPhotographersAddress());
				pstmt.setString(9, photographersBean.getPhotographersExperience());
				pstmt.setString(10, photographersBean.getPhotographersDescription());
				pstmt.setString(11, photographersBean.getCityId());
				pstmt.setString(12, photographersBean.getUsername());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return false;
	}

	public ArrayList<PhotographerBean> list() {
		ArrayList<PhotographerBean> list = new ArrayList<PhotographerBean>();

		String sql = "select * from photographers,city,country where photographers.cityId=city.cityId and city.countryName=country.countryName and photographersIsAvailable='Y'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				PhotographerBean photographersBean = null;
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setPrice(rs.getString("price"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					list.add(photographersBean);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return list;
	}

	public boolean delete(String photographersid) {

		String sql = "delete from photographers where photographersId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, photographersid);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

	public boolean reject(String photographersid) {

		String sql = "update photographers set photographersIsAvailable ='N' where photographersId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, photographersid);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

	public PhotographerBean getByPK(String photographersId) {

		PhotographerBean photographersBean = new PhotographerBean();
		String sql = "select * from photographers,city,country where photographersId=? and photographers.cityId=city.cityId and city.countryName=country.countryName";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, photographersId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return photographersBean;
	}

	public boolean update(PhotographerBean photographer) {

		String sql = "update photographers set photographersFirstName=?,photographersLastName=?,photographersContactNumber1=?,photographersContactNumber2=?,photographersEmailId=?,photographersDescription=?,cityId=?,photographersExperience=?,photographersAddress=?,username=?,startDate=?,endDate=?,price=? where photographersId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, photographer.getPhotographersFirstName());
				pstmt.setString(2, photographer.getPhotographersLastName());
				pstmt.setString(3, photographer.getPhotographersContact1());
				pstmt.setString(4, photographer.getPhotographersContact2());
				pstmt.setString(5, photographer.getPhotographersEmailId());
				pstmt.setString(6, photographer.getPhotographersDescription());
				pstmt.setString(7, photographer.getCityId());
				pstmt.setString(8, photographer.getPhotographersExperience());
				pstmt.setString(9, photographer.getPhotographersAddress());
				pstmt.setString(10, photographer.getUsername());
				pstmt.setString(11, photographer.getStartDate());
				pstmt.setString(12, photographer.getEndDate());
				pstmt.setString(13, photographer.getPrice());
				pstmt.setString(14, photographer.getPhotographersId());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return false;
	}

	public HashMap<String, Object> getList() {
		ArrayList<PhotographerBean> list = new ArrayList<PhotographerBean>();

		String sql = "select * from photographers,city,country where photographers.cityId=city.cityId and city.countryName=country.countryName and photographersIsAvailable='Y'";
		conn = DBConnection.getConnection();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");

		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				PhotographerBean photographersBean = null;
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setUsername(rs.getString("userName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					list.add(photographersBean);
					flag = true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		if (flag) {
			map.put("photographer", list);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", list.size() + "");
		return map;
	}

	public PhotographerBean login(String photographerEmail, String photographerPassword) {
		PhotographerBean photographersBean = null;
		String sql = "select * from photographers,city,country where photographers.cityId=city.cityId and city.countryName=country.countryName and photographersEmailId=? and photographersPassword=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, photographerEmail);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(photographerPassword));
				rs = pstmt.executeQuery();
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setPhotographersPassword(rs.getString("photographersPassword"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setUsername(rs.getString("userName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return photographersBean;
	}

	public boolean changePassword(String photographerId, String password) {

		String sql = "update photographers set photographersPassword=? where photographersId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, GenrateMathodsUtils.makeSHA512(password));
				pstmt.setString(2, photographerId);
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

	public boolean checkUsername(String username) {

		String sql = "select * from photographers where username=?";
		conn = DBConnection.getConnection();

		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return false;
	}

	public HashMap<String, Object> getByCityID(String cityId, String categoryId) {
		ArrayList<PhotographerBean> list = new ArrayList<PhotographerBean>();

		String sql = "select * from photographers,city,country,category where photographers.cityId=city.cityId and city.countryName=country.countryName and photographers.cityId=? and categoryId=? and category.photographersId=photographers.photographersId";
		conn = DBConnection.getConnection();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cityId);
				rs = pstmt.executeQuery();
				PhotographerBean photographersBean = null;
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setCategoryId(rs.getString("categoryId"));
					photographersBean.setCatrgoryName(rs.getString("catrgoryName"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					flag = true;
					list.add(photographersBean);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		if (flag) {
			map.put("photographer", list);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", list.size() + "");
		return map;
	}

	public HashMap<String, Object> getByEmailId(String emailId) {

		PhotographerBean photographersBean = new PhotographerBean();
		String sql = "select * from photographers,city,country where photographersEmailId = ? and photographers.cityId = city.cityId and city.countryName=country.countryName";

		HashMap<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, emailId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCountryCode(rs.getString("countryCode"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					flag = true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		if (flag) {
			map.put("photographer", photographersBean);
			map.put("code", 200);
			map.put(status, "success");
		}
		return map;

	}

	public HashMap<String, Object> getByUsername(String username) {

		PhotographerBean photographersBean = new PhotographerBean();
		String sql = "select * from photographers,city,country where username = ? and photographers.cityId = city.cityId and city.countryName=country.countryName";

		HashMap<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCountryCode(rs.getString("countryCode"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					flag = true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		if (flag) {
			map.put("photographer", photographersBean);
			map.put("code", 200);
			map.put(status, "success");
		}
		return map;

	}

	public List<PhotographerBean> pendingList() {
		ArrayList<PhotographerBean> list = new ArrayList<PhotographerBean>();

		String sql = "select * from photographers,city,country where photographers.cityId=city.cityId and city.countryName=country.countryName and photographers.photographersIsAvailable = 'P'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				PhotographerBean photographersBean = null;
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					list.add(photographersBean);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return list;
	}

	public PhotographerBean photographerDetails(String pid) {
		PhotographerBean photographersBean = new PhotographerBean();
		String sql = "select * from photographers,city,country where photographers.cityId=city.cityId and city.countryName=country.countryName and photographers.photographersId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setPrice(rs.getString("price"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return photographersBean;
	}

	public List<PhotographerBean> rejectedList() {
		ArrayList<PhotographerBean> list = new ArrayList<PhotographerBean>();

		String sql = "select * from photographers,city,country where photographers.cityId=city.cityId and city.countryName=country.countryName and photographers.photographersIsAvailable = 'N'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				PhotographerBean photographersBean = null;
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					list.add(photographersBean);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return list;
	}

	public boolean acceptPhotographer(String photographerId, String txtPrice, String txtStartDate, String txtEndDate, String password) {
		String sql = "update photographers set price=?,startDate=?,endDate=?,photographersIsAvailable=?,photographersPassword=? where photographersId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtPrice);
				pstmt.setString(2, txtStartDate);
				pstmt.setString(3, txtEndDate);
				pstmt.setString(4, "Y");
				pstmt.setString(5, GenrateMathodsUtils.makeSHA512(password));
				pstmt.setString(6, photographerId);
				
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public HashMap<String, Object> getByCityID(String cityId) {
		ArrayList<PhotographerBean> list = new ArrayList<PhotographerBean>();

		String sql = "select * from photographers where photographers.cityId=? ";
		conn = DBConnection.getConnection();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cityId);
				rs = pstmt.executeQuery();
				PhotographerBean photographersBean = null;
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					flag = true;
					list.add(photographersBean);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		if (flag) {
			map.put("photographer", list);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", list.size() + "");
		return map;
	}

	public HashMap<String, Object> getByCityCateID(String cityId, String categoryId) {
		ArrayList<PhotographerBean> list = new ArrayList<PhotographerBean>();

		String sql = "select * from photographers,album,category,city " + "where photographers.cityId=? and "
				+ "photographers.photographersId=album.photographersId and "
				+ "category.categoryId=album.categoryId and " + "city.cityId=photographers.cityId and "
				+ "category.categoryId=?";
		conn = DBConnection.getConnection();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cityId);
				pstmt.setString(2, categoryId);
				rs = pstmt.executeQuery();
				PhotographerBean photographersBean = null;
				while (rs.next()) {
					photographersBean = new PhotographerBean();
					photographersBean.setPhotographersId(rs.getString("photographersid"));
					photographersBean.setPhotographersContact1(rs.getString("photographersContactNumber1"));
					photographersBean.setPhotographersContact2(rs.getString("photographersContactNumber2"));
					photographersBean.setPhotographersEmailId(rs.getString("photographersEmailId"));
					photographersBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					photographersBean.setPhotographersLastName(rs.getString("photographersLastName"));
					photographersBean.setPhotographersIsAvailable(rs.getString("photographersIsAvailable"));
					photographersBean.setPhotographersAddress(rs.getString("photographersAddress"));
					photographersBean.setCityId(rs.getString("cityId"));
					photographersBean.setCountryName(rs.getString("countryName"));
					photographersBean.setCityName(rs.getString("cityName"));
					photographersBean.setPhotographersDescription(rs.getString("photographersDescription"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setCategoryId(rs.getString("categoryId"));
					photographersBean.setCatrgoryName(rs.getString("categoryname"));
					photographersBean.setPhotographersExperience(rs.getString("photographersExperience"));
					photographersBean.setUsername(rs.getString("username"));
					photographersBean.setAlbumName(rs.getString("albumname"));
					photographersBean.setStartDate(rs.getString("startDate"));
					photographersBean.setEndDate(rs.getString("endDate"));
					photographersBean.setPrice(rs.getString("price"));
					photographersBean.setPhotographersAlbumView(rs.getString("albumView"));
					photographersBean.setPhotographersPageView(rs.getString("pageView"));
					flag = true;
					list.add(photographersBean);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		if (flag) {
			map.put("photographer", list);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", list.size() + "");
		return map;
	}

	public boolean addView(String photographersID, String field) {

		int a = 0;
		String sql = "select * from photographers where photographersId=? and photographersIsAvailable='Y'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, photographersID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					a = rs.getInt(field);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		sql = "update photographers set " + field + "=? where photographersId=? and photographersIsAvailable='Y'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, ++a + "");
				pstmt.setString(2, photographersID);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return false;
	}

	public String isEmailExists(String emailId) {

		System.out.println("dddd "+emailId  );
		String sql = "select * from photographers where photographersEmailId=? and photographersIsAvailable='Y'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, emailId);
				System.out.println(emailId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					
					System.out.println(rs.getString("photographersId"));
					return rs.getString("photographersId");
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return "";
	}

	public boolean checkPassword(String oldPassword, String photographerId) {
		String sql = "select * from photographers where photographersId=? and photographersIsAvailable='Y' and photographersPassword=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, photographerId);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(oldPassword));
				rs = pstmt.executeQuery();
				while (rs.next()) {
					return true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

}