package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bean.CategoryBean;
import com.util.DBConnection;
import com.util.GenrateMathodsUtils;

public class CategoryDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public ArrayList<CategoryBean> list() {
		ArrayList<CategoryBean> list = new ArrayList<CategoryBean>();
		String sql = "select * from category";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				CategoryBean categoryBean = null;
				while (rs.next()) {
					categoryBean = new CategoryBean();
					categoryBean.setCategoryId(rs.getString("categoryId"));
					categoryBean.setCategoryName(rs.getString("categoryName"));
					list.add(categoryBean);
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

	public boolean insert(CategoryBean categoryBean) {

		String sql = "insert into category(categoryId,categoryName) values(?,?)";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
				pstmt.setString(2, categoryBean.getCategoryName());

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

	public boolean delete(String categoryId) {
		String sql = "delete from category where categoryId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, categoryId);
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

	public HashMap<String, Object> getList() {

		List<CategoryBean> listOfCategory = new ArrayList<CategoryBean>();
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		if (conn != null) {
			String selectSQL = "Select * from category order by categoryId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				CategoryBean Category = null;
				while (rs.next()) {
					Category = new CategoryBean();

					Category.setCategoryId(rs.getString("categoryId"));
					Category.setCategoryName(rs.getString("categoryName"));
					flag = true;
					listOfCategory.add(Category);
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
			map.put("category", listOfCategory);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", listOfCategory.size() + "");
		return map;

	}

	public CategoryBean getByPK(String categoryId) {
		String sql = "select * from category where categoryId=?";
		CategoryBean categoryBean = new CategoryBean();
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, categoryId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					categoryBean.setCategoryId(rs.getString("categoryId"));
					categoryBean.setCategoryName(rs.getString("categoryName"));
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

		return categoryBean;
	}

	public boolean alreadyExist(String category) {
		String sql = "select * from category where categoryName=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, category);
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

	public boolean update(CategoryBean categoryBean) {

		String sql = "update category set categoryName=? where categoryId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, categoryBean.getCategoryName());
				pstmt.setString(2, categoryBean.getCategoryId());
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

}
