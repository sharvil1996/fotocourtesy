package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bean.CountryBean;
import com.util.DBConnection;

public class CountryDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	boolean result = false;

	public boolean insert(CountryBean CountryBean) {
		conn = DBConnection.getConnection();

		if (conn != null) {
			String insertSQL = "INSERT INTO country(countryName,countryId,countryCode) values(?,?,?)";
			try {

				pstmt = conn.prepareStatement(insertSQL);
				pstmt.setString(1, CountryBean.getCountryName());
				pstmt.setString(2, CountryBean.getCountryId());
				pstmt.setString(3, CountryBean.getCountryCode());

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

	public HashMap<String, Object> list() {

		List<CountryBean> listOfCountry = new ArrayList<CountryBean>();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		if (conn != null) {
			String selectSQL = "Select * from country order by countryId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				CountryBean Country = null;
				while (rs.next()) {
					Country = new CountryBean();
					Country.setCountryId(rs.getString("countryId"));
					Country.setCountryName(rs.getString("countryName"));
					Country.setCountryCode(rs.getString("countryCode"));
					flag = true;
					listOfCountry.add(Country);
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
			map.put("country", listOfCountry);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", listOfCountry.size() + "");
		return map;

	}

	public List<CountryBean> getList() {

		List<CountryBean> listOfCountry = new ArrayList<CountryBean>();
		conn = DBConnection.getConnection();

		if (conn != null) {
			String selectSQL = "Select * from country order by countryName";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				CountryBean Country = null;
				while (rs.next()) {
					Country = new CountryBean();

					Country.setCountryId(rs.getString("countryId"));
					Country.setCountryName(rs.getString("countryName"));
					Country.setCountryCode(rs.getString("countryCode"));
					listOfCountry.add(Country);
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

		return listOfCountry;

	}

	public boolean delete(String CountryId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "DELETE FROM country WHERE countryId=?";

			try {
				pstmt = conn.prepareStatement(deleteSQL);

				pstmt.setString(1, CountryId);

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

	public CountryBean getByPK(String CountryId) {

		conn = DBConnection.getConnection();
		CountryBean Country = new CountryBean();

		if (conn != null) {
			String selectSQL = "Select * from country WHERE countryId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, CountryId);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					Country.setCountryId(rs.getString("countryId"));
					Country.setCountryName(rs.getString("countryNAME"));
					Country.setCountryCode(rs.getString("countryCode"));

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
		return Country;
	}

	public String getCountryID(String CountryName) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String selectSQL = "Select * from country WHERE countryName=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, CountryName);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					return rs.getString("countryId");

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
		return null;
	}

	public boolean update(CountryBean CountryBean) {
		conn = DBConnection.getConnection();

		if (conn != null) {
			String updateSQL = "UPDATE country set countryName=?,countryCode=? WHEREcountryId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1, CountryBean.getCountryName());
				pstmt.setString(2, CountryBean.getCountryCode());
				pstmt.setString(3, CountryBean.getCountryId());

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
