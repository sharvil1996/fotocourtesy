package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.bean.CategoryPriceBean;
import com.util.DBConnection;

public class CategoryPriceDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public boolean isExist(String photographerId, String categoryId) {
		String sql = "select * from categoryprice where categoryId = ? and photographersId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, categoryId);
				pstmt.setString(2, photographerId);
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

	public boolean insert(CategoryPriceBean categoryPriceBean) {

		String sql = "insert into categoryprice(categoryPriceId,photographersId,categoryId,price) values(?,?,?,?)";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {

				if (!isExist(categoryPriceBean.getPhotographersId(), categoryPriceBean.getCategoryId())) {
					conn = DBConnection.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, categoryPriceBean.getCategoryPriceId());
					pstmt.setString(2, categoryPriceBean.getPhotographersId());
					pstmt.setString(3, categoryPriceBean.getCategoryId());
					pstmt.setString(4, categoryPriceBean.getPrice());

					int rowsAffected = pstmt.executeUpdate();

					if (rowsAffected > 0) {
						return true;
					} else {
					}
				} else
					return false;
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

	public HashMap<String, Object> getList(String photographerId) {

		List<CategoryPriceBean> listOfCategory = new ArrayList<CategoryPriceBean>();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		if (conn != null) {
			String selectSQL = "select * from categoryprice where photographersId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, photographerId);
				rs = pstmt.executeQuery();
				CategoryPriceBean categoryPriceBean = null;
				while (rs.next()) {
					categoryPriceBean = new CategoryPriceBean();
					categoryPriceBean.setCategoryId(rs.getString("categoryId"));
					categoryPriceBean.setCategoryPriceId(rs.getString("categoryPriceId"));
					categoryPriceBean.setPrice(rs.getString("price"));
					categoryPriceBean.setPhotographersId(rs.getString("photographersId"));

					flag = true;
					listOfCategory.add(categoryPriceBean);
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
			map.put("categoryPrice", listOfCategory);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", listOfCategory.size() + "");
		return map;

	}

	public boolean delete(String categoryPriceId) {

		String sql = "delete from categoryprice where categoryPriceId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, categoryPriceId);

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

	public HashMap<String, Object> list() {

		List<CategoryPriceBean> listOfCategory = new ArrayList<CategoryPriceBean>();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		if (conn != null) {
			String selectSQL = "select * from categoryprice";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();

				CategoryPriceBean categoryPriceBean = null;
				while (rs.next()) {
					categoryPriceBean = new CategoryPriceBean();

					categoryPriceBean.setCategoryId(rs.getString("categoryId"));
					categoryPriceBean.setCategoryPriceId(rs.getString("categoryPriceId"));
					categoryPriceBean.setPrice(rs.getString("price"));
					categoryPriceBean.setPhotographersId(rs.getString("photographersId"));

					flag = true;
					listOfCategory.add(categoryPriceBean);
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
			map.put("categoryPrice", listOfCategory);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", listOfCategory.size() + "");
		return map;

	}

	public CategoryPriceBean getByPK(String categoryPriceId) {

		CategoryPriceBean categoryPriceBean = new CategoryPriceBean();
		String sql = "select * from categoryprice where categoryPriceId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, categoryPriceId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					categoryPriceBean.setCategoryId(rs.getString("categoryId"));
					categoryPriceBean.setCategoryPriceId(rs.getString("categoryPriceId"));
					categoryPriceBean.setPrice(rs.getString("price"));
					categoryPriceBean.setPhotographersId(rs.getString("photographersId"));
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

		return categoryPriceBean;
	}

	public boolean update(CategoryPriceBean categoryPriceBean) {

		String sql = "update categoryprice set photographersId=?,categoryId=?,price=? where categoryPriceId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {

				if (isExist(categoryPriceBean.getPhotographersId(), categoryPriceBean.getCategoryId())) {
					conn = DBConnection.getConnection();

					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, categoryPriceBean.getPhotographersId());
					pstmt.setString(2, categoryPriceBean.getCategoryId());
					pstmt.setString(3, categoryPriceBean.getPrice());
					pstmt.setString(4, categoryPriceBean.getCategoryPriceId());
					int rowsAffected = pstmt.executeUpdate();

					if (rowsAffected > 0) {
						return true;
					} else {
					}
				} else {
					return false;
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

	public ArrayList<CategoryPriceBean> getCategoryPriceList() {
		ArrayList<CategoryPriceBean> listOfCategory = new ArrayList<CategoryPriceBean>();
		conn = DBConnection.getConnection();
		if (conn != null) {
			String selectSQL = "select * from categoryprice,photographers,category where category.categoryId=categoryprice.categoryId and photographers.photographersId=categoryprice.photographersId";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				CategoryPriceBean categoryPriceBean = null;
				while (rs.next()) {
					categoryPriceBean = new CategoryPriceBean();
					categoryPriceBean.setCategoryId(rs.getString("categoryId"));
					categoryPriceBean.setCategoryPriceId(rs.getString("categoryPriceId"));
					categoryPriceBean.setPrice(rs.getString("price"));
					categoryPriceBean.setPhotographersId(rs.getString("photographersId"));
					categoryPriceBean.setCategoryName(rs.getString("categoryName"));
					categoryPriceBean.setPhotographerFirstName(rs.getString("photographersFirstName"));
					categoryPriceBean.setPhotographerLastName(rs.getString("photographersLastName"));
					categoryPriceBean.setPhotographerUserName(rs.getString("userName"));

					listOfCategory.add(categoryPriceBean);
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
		return listOfCategory;
	}
}
