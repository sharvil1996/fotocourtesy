package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.bean.AdminBean;
import com.util.GenrateMathodsUtils;
import com.util.DBConnection;

public class AdminDAO {
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public boolean insert(AdminBean adminBean) {

		String sql = "insert into admin(adminId,adminFirstname,adminLastname,adminEmailId,adminContactNumber1,adminContactNumber2,adminpassword) values(?,?,?,?,?,?,?)";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
				pstmt.setString(2, adminBean.getFirstName());
				pstmt.setString(3, adminBean.getLastName());
				pstmt.setString(4, adminBean.getEmailId());
				pstmt.setString(5, adminBean.getContact1());
				pstmt.setString(6, adminBean.getContact2());
				pstmt.setString(7, adminBean.getPassword());

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

	public boolean changePassword(String adminId, String password) {

		String sql = "update admin set adminPassword=? where adminId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, password);
				pstmt.setString(2, adminId);
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

	public ArrayList<AdminBean> list() {
		ArrayList<AdminBean> list = new ArrayList<AdminBean>();

		String sql = "select * from admin";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				AdminBean adminBean = null;
				while (rs.next()) {
					adminBean = new AdminBean();
					adminBean.setAdminId(rs.getString("adminid"));
					adminBean.setContact1(rs.getString("adminContactNumber1"));
					adminBean.setContact2(rs.getString("adminContactNumber2"));
					adminBean.setEmailId(rs.getString("adminEmailId"));
					adminBean.setFirstName(rs.getString("adminFirstName"));
					adminBean.setLastName(rs.getString("adminLastName"));
					adminBean.setIsAvailable(rs.getString("adminIsAvailable"));
					list.add(adminBean);
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

	public HashMap<String, Object> getList() {
		ArrayList<AdminBean> list = new ArrayList<AdminBean>();

		String sql = "select * from admin";
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
				AdminBean adminBean = null;
				while (rs.next()) {
					adminBean = new AdminBean();
					adminBean.setAdminId(rs.getString("adminid"));
					adminBean.setContact1(rs.getString("adminContactNumber1"));
					adminBean.setContact2(rs.getString("adminContactNumber2"));
					adminBean.setEmailId(rs.getString("adminEmailId"));
					adminBean.setFirstName(rs.getString("adminFirstName"));
					adminBean.setLastName(rs.getString("adminLastName"));
					adminBean.setIsAvailable(rs.getString("adminIsAvailable"));
					list.add(adminBean);
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
			map.put("admin", list);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", list.size() + "");
		return map;

	}

	public boolean delete(String adminid) {

		String sql = "delete from admin where adminId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, adminid);

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

	public AdminBean getByPK(String adminId) {

		AdminBean adminBean = new AdminBean();
		String sql = "select * from admin where adminId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, adminId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					adminBean.setAdminId(rs.getString("adminid"));
					adminBean.setContact1(rs.getString("adminContactNumber1"));
					adminBean.setContact2(rs.getString("adminContactNumber2"));
					adminBean.setEmailId(rs.getString("adminEmailId"));
					adminBean.setFirstName(rs.getString("adminFirstName"));
					adminBean.setLastName(rs.getString("adminLastName"));
					adminBean.setIsAvailable(rs.getString("adminIsAvailable"));
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

		return adminBean;
	}

	public boolean update(AdminBean adminBean) {

		String sql = "update admin set adminFirstName=?,adminLastName=?,adminContactNumber1=?,adminContactNumber2=?,adminEmailId=?,adminIsAvailable=? where adminId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, adminBean.getFirstName());
				pstmt.setString(2, adminBean.getLastName());
				pstmt.setString(3, adminBean.getContact1());
				pstmt.setString(4, adminBean.getContact2());
				pstmt.setString(5, adminBean.getEmailId());
				pstmt.setString(6, adminBean.getIsAvailable());
				pstmt.setString(7, adminBean.getAdminId());

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

	public AdminBean login(String adminEmail, String adminPassword) {
		AdminBean adminBean = null;
		String sql = "select * from admin where adminEmailId=? and adminPassword=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, adminEmail);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(adminPassword));
				rs = pstmt.executeQuery();
				while (rs.next()) {
					adminBean = new AdminBean();
					adminBean.setAdminId(rs.getString("adminId"));
					adminBean.setContact1(rs.getString("adminContactNumber1"));
					adminBean.setContact2(rs.getString("adminContactNumber2"));
					adminBean.setPassword(rs.getString("adminPassword"));
					adminBean.setEmailId(rs.getString("adminEmailId"));
					adminBean.setFirstName(rs.getString("adminFirstName"));
					adminBean.setLastName(rs.getString("adminLastName"));
					adminBean.setIsAvailable(rs.getString("adminIsAvailable"));
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
		return adminBean;
	}

}