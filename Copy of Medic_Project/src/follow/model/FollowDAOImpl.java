package follow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import user.model.UserVO;

public class FollowDAOImpl implements FollowDAO{
	
	DataSource ds = null;
	
	private FollowDAOImpl() { //1
		
		try{
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/OracleJNDI"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static FollowDAO followDAO = null; //2
	
	public static FollowDAO getInstance(){ //3
		if(followDAO == null){ //4
			followDAO = new FollowDAOImpl(); //5
		}
		return followDAO; //6
	}
	
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	private void dbClose(ResultSet rs, PreparedStatement ps, Connection conn){
		if (rs != null) try{rs.close();} catch(Exception e){}
		if (ps != null) try{ps.close();} catch(Exception e){}
		if (conn != null) try{conn.close();} catch(Exception e){}
	}
	
	private void dbClose(PreparedStatement ps, Connection conn){
		if (ps != null) try{ps.close();} catch(Exception e){}
		if (conn != null) try{conn.close();} catch(Exception e){}
	}

	@Override
	public List<UserVO> getFollowList(String user_id, FollowListVO followListVO) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<UserVO> list = new ArrayList<UserVO>(); 
		UserVO userVO = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.*");
		sql.append(" from (select rownum as rnum, a.*");
		sql.append(" 	   from	   (select m.*");
		sql.append(" 		     	from (select follow_id");
		sql.append(" 			          from t_follow");
		sql.append(" 			          where user_id = ?) f join t_member m");
		sql.append(" 			    on f.follow_id = m.user_id) a ) b");
		sql.append(" where rnum between ? and ?");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user_id);
			ps.setInt(2, 1);
			ps.setInt(3, 5);
			rs = ps.executeQuery();
			
			while(rs.next()){
				userVO = new UserVO();
				userVO.setUser_aboutme(rs.getString("user_aboutme"));
				userVO.setUser_birth(rs.getString("user_birth"));
				userVO.setUser_id(rs.getString("user_id"));
				userVO.setUser_name(rs.getString("user_name"));
				userVO.setUser_gender(rs.getString("user_gender"));
				userVO.setUser_photo(rs.getString("user_photo"));
				list.add(userVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbClose(rs, ps, conn);
		}
		return list;
	}
	
	@Override
	public Long getFollowCnt(String user_id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Long followCnt = 0l;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) as cnt");
		sql.append(" from (select follow_id ");
		sql.append(" 	  from t_follow ");
		sql.append(" 	  where user_id = ?) f join t_member m");
		sql.append(" on f.follow_id = m.user_id");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				followCnt = rs.getLong("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbClose(rs, ps, conn);
		}
		return followCnt;
	}
	
}
