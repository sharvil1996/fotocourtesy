package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bean.CityBean;
import com.util.DBConnection;

public class CityDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;
	boolean result = false;

	public HashMap<String, Object> list() {

		List<CityBean> listOfCities = new ArrayList<CityBean>();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		if (conn != null) {
			String selectSQL = "select * from city,country where city.countryName=country.countryName";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				CityBean cityBean = null;
				while (rs.next()) {
					cityBean = new CityBean();
					cityBean.setCityId(rs.getString("cityId"));
					cityBean.setCityName(rs.getString("cityName"));
					cityBean.setCountryName(rs.getString("countryName"));
					cityBean.setCountryId(rs.getString("countryId"));
					flag = true;
					listOfCities.add(cityBean);
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
			map.put("city", listOfCities);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", listOfCities.size() + "");
		return map;

	}

	public boolean insert(CityBean CityBean) {
		conn = DBConnection.getConnection();

		if (conn != null) {
			String insertSQL = "INSERT INTO City(cityName,cityId,countryName) values(?,?,?)";
			try {

				pstmt = conn.prepareStatement(insertSQL);
				pstmt.setString(1, CityBean.getCityName());
				pstmt.setString(2, CityBean.getCityId());
				pstmt.setString(3, CityBean.getCountryName());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;
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

		return result;
	}

	public ArrayList<CityBean> getList() {
		ArrayList<CityBean> list = new ArrayList<CityBean>();
		String sql = "select * from city,country where city.countryName=country.countryName order by city.cityName";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				CityBean cityBean = null;
				while (rs.next()) {
					cityBean = new CityBean();
					cityBean.setCityId(rs.getString("cityId"));
					cityBean.setCityName(rs.getString("cityName"));
					cityBean.setCountryName(rs.getString("countryName"));
					cityBean.setCountryId(rs.getString("countryId"));
					list.add(cityBean);
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

	public ArrayList<CityBean> getCityList(String countryName) {
		ArrayList<CityBean> list = new ArrayList<CityBean>();
		String sql = "select * from city,country where city.countryName=country.countryName and city.countryName=? order by city.cityName";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, countryName);
				rs = pstmt.executeQuery();
				CityBean cityBean = null;
				while (rs.next()) {
					cityBean = new CityBean();
					cityBean.setCityId(rs.getString("cityId"));
					cityBean.setCityName(rs.getString("cityName"));
					cityBean.setCountryName(rs.getString("countryName"));
					cityBean.setCountryId(rs.getString("countryId"));
					list.add(cityBean);
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

	public boolean delete(String CityId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "DELETE FROM City WHERE cityId=?";

			try {
				pstmt = conn.prepareStatement(deleteSQL);

				pstmt.setString(1, CityId);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;
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
		return result;
	}

	public CityBean getByPK(String CityId) {

		conn = DBConnection.getConnection();
		CityBean cityBean = new CityBean();

		if (conn != null) {
			String selectSQL = "select * from city,country where city.countryName=country.countryName and cityId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, CityId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					cityBean.setCityId(rs.getString("cityId"));
					cityBean.setCityName(rs.getString("cityName"));
					cityBean.setCountryName(rs.getString("countryName"));
					cityBean.setCountryId(rs.getString("countryId"));
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
		return cityBean;
	}

	public boolean update(CityBean CityBean) {
		conn = DBConnection.getConnection();

		if (conn != null) {
			String updateSQL = "UPDATE City set CityName=?,countryName=? WHERE CityID=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1, CityBean.getCityName());
				pstmt.setString(2, CityBean.getCountryName());
				pstmt.setString(3, CityBean.getCityId());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;
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
		return result;
	}
}
