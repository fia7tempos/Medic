package comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommentDAOImpl implements CommentDAO {
	private static final Logger logger = LoggerFactory.getLogger(CommentDAOImpl.class);
	private static CommentDAOImpl commentDAO  = null;
	DataSource ds = null;		
	
	public static CommentDAO getInstance(){
		if(commentDAO == null){
			commentDAO = new CommentDAOImpl();
		}
		return commentDAO;
	}	
	private CommentDAOImpl(){
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/OracleJNDI");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Connection getConnection() throws SQLException{
		return ds.getConnection();
		}
		
	private void dbclose(ResultSet rs, PreparedStatement ps, Connection conn) {
		if(rs !=null)try {rs.close();} catch (Exception e) {}
		if(ps !=null)try {ps.close();} catch (Exception e) {}
		if(conn !=null)try {conn.close();} catch (Exception e) {}
	}
	private void dbclose(PreparedStatement ps, Connection conn) {
		if(ps !=null)try {ps.close();} catch (Exception e) {}
		if(conn !=null)try {conn.close();} catch (Exception e) {}
	}
	@Override
	public boolean saveComment(CommentVO commentVO) {
		boolean result = false; 
		Connection conn = null;
		PreparedStatement ps = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO t_comment(no, article_no, user_id, content, regdate)");
		sql.append(" VALUES (seq_comment.nextval, ?, ?, ?, sysdate)");

		try {
			conn =getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, commentVO.getArticle_no());
			ps.setString(2, commentVO.getUser_id());
			ps.setString(3, commentVO.getContent());
			if(ps.executeUpdate()==1){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose(ps, conn);
		}
		return result;
	}
	@Override
	public List<CommentVO> CommentViewer(long article_no) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<CommentVO> list = new ArrayList<CommentVO>();


		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.*");
		sql.append(" from (SELECT c.no, c.article_no, c.user_id, c.content, m.user_photo");
		sql.append(" 		, DECODE(to_char(c.regdate,'YYYY-MM-DD'), to_char(sysdate,'YYYY-MM-DD')");
		sql.append(" 					,to_char(c.regdate, 'HH24:MI:SS')");
		sql.append(" 					,to_char(c.regdate, 'YYYY-MM-DD')) as regdate ");
		sql.append(" FROM t_comment c LEFT OUTER JOIN t_member m");
		sql.append(" ON c.user_id = m.user_id ) a");
		sql.append(" WHERE article_no = ?");
		sql.append(" order by no DESC ");

		

		try {			
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, article_no);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CommentVO commentVO = new CommentVO();
				commentVO.setNo(rs.getLong("no"));
				commentVO.setArticle_no(rs.getLong("article_no"));
				commentVO.setUser_id(rs.getString("user_id"));
				commentVO.setContent(rs.getString("content"));
				commentVO.setRegdate(rs.getString("regdate"));
				commentVO.setUser_photo(rs.getString("user_photo"));
				list.add(commentVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose(rs, ps, conn);
		}		
		return list;
	}
	
	
	
	
}
