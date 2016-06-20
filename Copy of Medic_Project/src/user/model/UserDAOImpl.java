package user.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAOImpl implements UserDAO{
	
	DataSource ds = null;
	
	private UserDAOImpl() { //1
		
		try{
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/OracleJNDI"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static UserDAO userDAO = null; //2
	
	public static UserDAO getInstance(){ //3
		if(userDAO == null){ //4
			userDAO = new UserDAOImpl(); //5
		}
		return userDAO; //6
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
	public UserVO loginAction(String user_id, String user_pw) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserVO userInfo = new UserVO();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT user_name");
		sql.append(" FROM t_member");
		sql.append(" WHERE user_id= ? AND user_pw= ?");

		try {
			conn = getConnection();
			ps= conn.prepareStatement(sql.toString());
			ps.setString(1, user_id);
			ps.setString(2, user_pw);
			rs = ps.executeQuery();
			
			if(rs.next()){
				
				userInfo.setUser_id(user_id);
				userInfo.setUser_name(rs.getString("user_name"));
//				System.out.println("sql userInfo값"+userInfo);
			}else{
				userInfo=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(rs, ps, conn);
		}
		return userInfo;
	}

	@Override
	public int CountUserID(String user_id) {
		int count = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) AS cnt");
		sql.append(" FROM t_member");
		sql.append(" WHERE user_id=?");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cnt");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs, ps, conn);
		}
		return count;
	}

	@Override
	public UserVO getUserProfile(String user_id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UserVO userInfo = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT *");
		sql.append(" FROM t_member");
		sql.append(" WHERE user_id= ?");

		try {
			conn = getConnection();
			ps= conn.prepareStatement(sql.toString());
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				userInfo = new UserVO();
				userInfo.setUser_id(user_id);
				userInfo.setUser_name(rs.getString("user_name"));
				userInfo.setUser_email(rs.getString("user_email"));
				userInfo.setUser_gender(rs.getString("user_gender"));
				userInfo.setUser_photo(rs.getString("user_photo"));
				userInfo.setUser_aboutme(rs.getString("user_aboutme"));
				userInfo.setUser_birth(rs.getString("USER_BIRTH").substring(0,11));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(rs, ps, conn);
		}
		return userInfo;
	}

	@Override
	public boolean registUser(UserVO userVO) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" INSERT INTO t_member(user_id, user_name, user_email, user_gender,");
		sql.append(" 			user_birth, user_photo, regdate, user_pw, user_aboutme)");
		sql.append(" VALUES(?, ?, ?, ?, ");
		sql.append(" 			?, ?, sysdate, ?, ? )");
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userVO.getUser_id());
			ps.setString(2, userVO.getUser_name());
			ps.setString(3, userVO.getUser_email());
			ps.setString(4, userVO.getUser_gender());
			ps.setString(5,	userVO.getUser_birth());
			ps.setString(6, userVO.getUser_photo());
			ps.setString(7, userVO.getUser_pw());
			ps.setString(8, userVO.getUser_aboutme());
			if(ps.executeUpdate()==1){
				result=true;
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(ps, conn);
		}
		
		
		return result;
	}

	@Override
	public boolean registFBUser(UserVO userVO) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" INSERT INTO t_member(user_id, user_name, user_email, user_gender,");
		sql.append(" 			user_photo, regdate)");
		sql.append(" VALUES(?, ?, ?, ?, ");
		sql.append(" 			?, sysdate )");
		
		try {
			conn= getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userVO.getUser_id());
			ps.setString(2, userVO.getUser_name());
			ps.setString(3, userVO.getUser_email());
			ps.setString(4, userVO.getUser_gender());
			ps.setString(5, userVO.getUser_photo());
			if(ps.executeUpdate()==1){
				result=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(ps, conn);
		}
		
		return result;
	}
	
	public boolean updateProfile(UserVO userVO) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update t_member");
		sql.append(" set user_aboutme = ?");
		sql.append(" where user_id = ?");
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userVO.getUser_aboutme());
			ps.setString(2, userVO.getUser_id());
			
			if(ps.executeUpdate()==1){
				result=true;
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(ps, conn);
		}
		
		
		return result;
	}

	@Override
	public Long CountUserArticle(String user_id) {
		long count = -1;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) AS cnt");
		sql.append(" FROM t_article");
		sql.append(" WHERE user_id=?");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("cnt");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs, ps, conn);
		}
		return count;
	}

	@Override
	public List<UserVO> getUserFollow(String searchKeyword) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<UserVO> list = new ArrayList<UserVO>();
		
		UserVO userInfo = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT *");
		sql.append(" FROM t_member");
		sql.append(" WHERE user_id= ? or user_name= ? or user_email= ?");

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			
			ps.setString(1, searchKeyword);
			ps.setString(2, searchKeyword);
			ps.setString(3, searchKeyword);
			
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				userInfo = new UserVO();
				userInfo.setUser_id(rs.getString("user_id"));
				userInfo.setUser_name(rs.getString("user_name"));
				userInfo.setUser_email(rs.getString("user_email"));
				userInfo.setUser_gender(rs.getString("user_gender"));
				userInfo.setUser_photo(rs.getString("user_photo"));
				userInfo.setUser_aboutme(rs.getString("user_aboutme"));
				userInfo.setUser_birth(rs.getString("USER_BIRTH").substring(0, 11));
				list.add(userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(rs, ps, conn);
		}
		return list;
	}

	@Override
	public boolean addFollower(String addFollwer_id, String user_id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(*) as cnt");
		sql.append(" from t_follow");
		sql.append(" where user_id = ? and follow_id = ?");
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append(" insert into T_FOLLOW(no,user_id,follow_id)");
		sql2.append(" values (seq_follow.nextval, ?, ?)");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			
			ps.setString(1, user_id);
			ps.setString(2, addFollwer_id);
			rs = ps.executeQuery();
			
			if(rs.next() && rs.getInt("cnt") == 0){
				ps2 = conn.prepareStatement(sql2.toString());
				ps2.setString(1, user_id);
				ps2.setString(2, addFollwer_id);
				ps2.executeUpdate();
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(rs, ps, conn);
		}
	
		
		return result;
	}
	
}
