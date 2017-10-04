package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bean.AlbumBean;
import com.util.GenrateMathodsUtils;
import com.util.DBConnection;

public class AlbumDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public boolean insert(AlbumBean albumBean) {

		String sql = "insert into album(albumId,albumName,albumImageLink,photographersId,albumDescription) values(?,?,?,?,?)";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {

				String sql1 = "select * from photographers where photographersIsAvailable='Y' and photographers.photographersId=?";
				conn = DBConnection.getConnection();
				if (conn != null) {
					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(1, albumBean.getPhotographerId());
					rs = pstmt.executeQuery();
					while (rs.next()) {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
						pstmt.setString(2, albumBean.getAlbumName());
						pstmt.setString(3, albumBean.getAlbumLink());
						pstmt.setString(4, albumBean.getPhotographerId());
						pstmt.setString(5, albumBean.getAlbumDescription());
						int rowsAffected = pstmt.executeUpdate();

						if (rowsAffected > 0) {
							return true;
						} else {
						}
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

	public boolean isAlbumAvailable(AlbumBean albumBean) {

		String sql = "select * from album where photographersId=? and albumName=?";
		conn = DBConnection.getConnection();
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, albumBean.getPhotographerId());
				pstmt.setString(2, albumBean.getAlbumName());
				rs = pstmt.executeQuery();

				while (rs.next()) {
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

		return new AlbumDAO().insert(albumBean);
	}

	public HashMap<String, Object> getList() {
		ArrayList<AlbumBean> list = new ArrayList<AlbumBean>();
		AlbumBean albumBean = new AlbumBean();
		String sql = "select * from album,photographers where album.photographersId=photographers.photographersId";
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

				while (rs.next()) {
					albumBean = new AlbumBean();
					albumBean.setAlbumDescription(rs.getString("albumDescription"));
					albumBean.setAlbumId(rs.getString("albumId"));
					albumBean.setAlbumLink(rs.getString("albumImageLink"));
					albumBean.setAlbumName(rs.getString("albumName"));
					albumBean.setPhotographerId(rs.getString("photographersId"));
					albumBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					albumBean.setPhotographersLastName(rs.getString("photographersLastName"));
					list.add(albumBean);
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
			map.put("album", list);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", list.size() + "");
		return map;

	}

	public List<AlbumBean> list() {
		ArrayList<AlbumBean> list = new ArrayList<AlbumBean>();
		AlbumBean albumBean = new AlbumBean();
		String sql = "select * from album,photographers where album.photographersId=photographers.photographersId";
		conn = DBConnection.getConnection();
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					albumBean = new AlbumBean();
					albumBean.setAlbumDescription(rs.getString("albumDescription"));
					albumBean.setAlbumId(rs.getString("albumId"));
					albumBean.setAlbumLink(rs.getString("albumImageLink"));
					albumBean.setAlbumName(rs.getString("albumName"));
					albumBean.setPhotographerId(rs.getString("photographersId"));
					albumBean.setPhotographersFirstName(rs.getString("photographersFirstName"));
					albumBean.setPhotographersLastName(rs.getString("photographersLastName"));
					list.add(albumBean);
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

	public boolean delete(String albumid) {
		String sql = "delete from album where albumId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, albumid);

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

	public AlbumBean getByPK(String albumId) {

		AlbumBean albumBean = new AlbumBean();
		String sql = "select * from album where albumId = ?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, albumId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					albumBean.setAlbumDescription(rs.getString("albumDescription"));
					albumBean.setAlbumId(rs.getString("albumId"));
					albumBean.setAlbumLink(rs.getString("albumImageLink"));
					albumBean.setAlbumName(rs.getString("albumName"));
					albumBean.setPhotographerId(rs.getString("photographersId"));
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

		return albumBean;
	}

	public boolean update(AlbumBean albumBean) {

		String sql = "update album set albumName=?,albumImageLink=?,photographersId=?,albumDescription=? where albumId=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, albumBean.getAlbumName());
				pstmt.setString(2, albumBean.getAlbumLink());
				pstmt.setString(3, albumBean.getPhotographerId());
				pstmt.setString(4, albumBean.getAlbumDescription());
				pstmt.setString(5, albumBean.getAlbumId());
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

	public HashMap<String, Object> getAlbumPhotograoherList(String photographerId) {
		ArrayList<AlbumBean> list = new ArrayList<AlbumBean>();
		AlbumBean albumBean = new AlbumBean();
		String sql = "select * from album where photographersid=?";
		conn = DBConnection.getConnection();
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean flag = false;
		String status = "status";
		map.put("code", "404");
		map.put(status, "fail");
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, photographerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					albumBean = new AlbumBean();
					albumBean.setAlbumDescription(rs.getString("albumDescription"));
					albumBean.setAlbumId(rs.getString("albumId"));
					albumBean.setAlbumLink(rs.getString("albumImageLink"));
					albumBean.setAlbumName(rs.getString("albumName"));
					albumBean.setPhotographerId(rs.getString("photographersId"));
					list.add(albumBean);
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
			map.put("album", list);
			map.put("code", 200);
			map.put(status, "success");
		}
		map.put("count", list.size() + "");
		return map;

	}

}
