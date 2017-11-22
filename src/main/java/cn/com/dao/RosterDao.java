package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import cn.com.entity.Roster;
import cn.com.util.DBUtil;

public class RosterDao {
	
	/** 新建roster */
	public void save(Roster roster) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO test VALUES(?, ?, ?, ? )";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, roster.getPayAcc());
			ps.setString(2, roster.getReyAcc());
			ps.setLong(3, roster.getEnableTime());
			ps.setString(4, roster.getRemark());
			ps.executeUpdate();
//			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("新加名单数据失败");
		}finally {
			DBUtil.closeConnection(conn);
		}
		
	}
	
	
	public static void main(String[] args) {
		RosterDao dao = new RosterDao();
		Roster r = new Roster();
		r.setPayAcc("qaz");
		r.setReyAcc("wsx");
		r.setEnableTime(System.currentTimeMillis());
		r.setRemark("edc");
		dao.save(r);
	}
	
	
	

}
