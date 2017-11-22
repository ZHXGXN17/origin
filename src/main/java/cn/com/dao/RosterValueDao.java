package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import cn.com.entity.RosterValue;
import cn.com.util.DBUtil;

public class RosterValueDao {
	
	public void save(RosterValue rosterValue) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO tms_mgr_rostervalue VALUES(?, ?, ?, ?, ?, null, ?, null)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rosterValue.getRostervalueId());
			ps.setInt(2, rosterValue.getRosterId());
			ps.setString(3, rosterValue.getRosterValue());
			ps.setString(4, rosterValue.getRemark());
			ps.setLong(5, rosterValue.getEnableTime());
			ps.setLong(6, rosterValue.getCreateTime());
			ps.executeUpdate();
//			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn);
		}
	}

}
