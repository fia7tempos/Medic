package article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ArticleDAOImpl implements ArticleDAO {	
	private static ArticleDAO articleDAO = new ArticleDAOImpl();
	DataSource ds = null;
	
	public static ArticleDAO getInstance() {
		return articleDAO;
	}
	
	private ArticleDAOImpl(){
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/OracleJNDI");
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}
	
	private Connection getConnection() throws SQLException {		
		return ds.getConnection();
	}	

	private void dbClose(PreparedStatement ps, Connection conn) {
		if(ps!=null) try{ps.close();} catch(Exception e){}
		if(conn!=null) try{conn.close();} catch(Exception e){}
	}
	
	private void dbClose(ResultSet rs, PreparedStatement ps, Connection conn) {
		if(rs != null) try{rs.close();} catch(Exception e){}
		if(ps != null) try{ps.close();} catch(Exception e){}
		if(conn != null) try{conn.close();} catch(Exception e){}
	}

	@Override
	public boolean insertArticle(ArticleVO articleVO) {
		boolean result = false;	
		Connection conn = null;
		PreparedStatement ps = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_ARTICLE(no,content,user_id,image,video,latitude,longitude,hashtag,category)");
		sql.append(" values (seq_article.nextval,?,?,?,?,?,?,?,?)");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, articleVO.getContent());
			ps.setString(2, articleVO.getUser_id());
			ps.setString(3, articleVO.getImage());
			ps.setString(4, articleVO.getVideo());
			ps.setDouble(5, articleVO.getLatitude());
			ps.setDouble(6, articleVO.getLongitude());
			ps.setString(7, articleVO.getHashtag());
			ps.setString(8, articleVO.getCategory());
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
	public ArticleVO getArticle(long no) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArticleVO articleVO = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT no, content, user_id, image, video, latitude,longitude,hashtag,category,good,to_char(regdate) as regdate ");
		sql.append(" FROM t_article ");
		sql.append(" WHERE no=? ");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				articleVO = new ArticleVO();
				
				articleVO.setNo(rs.getLong("no"));
				articleVO.setContent(rs.getString("content"));
				articleVO.setUser_id(rs.getString("user_id"));
				articleVO.setImage(rs.getString("image"));
				articleVO.setVideo(rs.getString("video"));
				articleVO.setLatitude(rs.getDouble("latitude"));
				articleVO.setLongitude(rs.getDouble("longitude"));
				articleVO.setHashtag(rs.getString("hashtag"));
				articleVO.setCategory(rs.getString("category"));
				articleVO.setGood(rs.getInt("good"));
				articleVO.setRegdate(rs.getString("regdate"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs, ps, conn);
		}
		return articleVO;
	}
	
	@Override
	public List<ArticleVO> getArticleList(PageNation pageNation) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT B.* ");
		sql.append(" FROM	(SELECT rownum as rnum, A.* ");
		sql.append(" 		FROM	(SELECT no, content, user_id,image,video,latitude,longitude,hashtag,category,good ");
		sql.append(" 					   ,DECODE(to_char(SYSDATE,'YYYY-MM-DD'), to_char(regdate,'YYYY-MM-DD'), to_char(regdate, 'HH24:MI:SS') ");
		sql.append(" 																				    , to_char(regdate, 'YYYY-MM-DD HH24:MI:SS')) as regdate ");
		sql.append(" 					FROM t_article ");
		sql.append(" 					ORDER BY no DESC) A) B ");
		sql.append(" WHERE ? <= rnum AND rnum <= ? ");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pageNation.getStartNum());
			ps.setLong(2, pageNation.getEndNum());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ArticleVO articleVO = new ArticleVO();
				articleVO.setNo(rs.getLong("no"));
				articleVO.setContent(rs.getString("content"));
				articleVO.setUser_id(rs.getString("user_id"));
				articleVO.setImage(rs.getString("image"));
				articleVO.setVideo(rs.getString("video"));
				articleVO.setLatitude(rs.getDouble("latitude"));
				articleVO.setLongitude(rs.getDouble("longitude"));
				articleVO.setHashtag(rs.getString("hashtag"));
				articleVO.setCategory(rs.getString("category"));
				articleVO.setGood(rs.getInt("good"));
				articleVO.setRegdate(rs.getString("regdate"));
				
				list.add(articleVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs, ps, conn);
		}
		
		return list;
	}
	
	public List<ArticleVO> getArticleKeywordList(PageNation pageNation) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT B.* ");
		sql.append(" FROM	(SELECT rownum as rnum, A.* ");
		sql.append(" 		FROM	(SELECT no, content, user_id,image,video,latitude,longitude,hashtag,category,good ");
		sql.append(" 					   ,DECODE(to_char(SYSDATE,'YYYY-MM-DD'), to_char(regdate,'YYYY-MM-DD'), to_char(regdate, 'HH24:MI:SS') ");
		sql.append(" 																				    , to_char(regdate, 'YYYY-MM-DD HH24:MI:SS')) as regdate ");
		sql.append(" 					FROM t_article ");
		sql.append(" 					ORDER BY no DESC) A) B ");
		sql.append(" WHERE ? <= rnum AND rnum <= ? and CONTENT like '%'|| ? ||'%' or HASHTAG like '%#' || ? || '%'");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pageNation.getStartNum());
			ps.setLong(2, pageNation.getEndNum());
			ps.setString(3, pageNation.getKeyword());
			ps.setString(4, pageNation.getKeyword());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ArticleVO articleVO = new ArticleVO();
				articleVO.setNo(rs.getLong("no"));
				articleVO.setContent(rs.getString("content"));
				articleVO.setUser_id(rs.getString("user_id"));
				articleVO.setImage(rs.getString("image"));
				articleVO.setVideo(rs.getString("video"));
				articleVO.setLatitude(rs.getDouble("latitude"));
				articleVO.setLongitude(rs.getDouble("longitude"));
				articleVO.setHashtag(rs.getString("hashtag"));
				articleVO.setCategory(rs.getString("category"));
				articleVO.setGood(rs.getInt("good"));
				articleVO.setRegdate(rs.getString("regdate"));
				
				list.add(articleVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs, ps, conn);
		}
		
		return list;
	}
	
	@Override
	public List<ArticleVO> getUserArticleList(String user_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT B.* ");
		sql.append(" FROM	(SELECT rownum as rnum, A.* ");
		sql.append(" 		FROM	(SELECT no, content, user_id,image,video,latitude,longitude,hashtag,category,good ");
		sql.append(" 					   ,DECODE(to_char(SYSDATE,'YYYY-MM-DD'), to_char(regdate,'YYYY-MM-DD'), to_char(regdate, 'HH24:MI:SS') ");
		sql.append(" 																				    , to_char(regdate, 'YYYY-MM-DD HH24:MI:SS')) as regdate ");
		sql.append(" 					FROM t_article ");
		sql.append(" 					ORDER BY no DESC) A) B ");
		sql.append(" WHERE user_id = ? ");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ArticleVO articleVO = new ArticleVO();
				articleVO.setNo(rs.getLong("no"));
				articleVO.setContent(rs.getString("content"));
				articleVO.setUser_id(rs.getString("user_id"));
				articleVO.setImage(rs.getString("image"));
				articleVO.setVideo(rs.getString("video"));
				articleVO.setLatitude(rs.getDouble("latitude"));
				articleVO.setLongitude(rs.getDouble("longitude"));
				articleVO.setHashtag(rs.getString("hashtag"));
				articleVO.setCategory(rs.getString("category"));
				articleVO.setGood(rs.getInt("good"));
				articleVO.setRegdate(rs.getString("regdate"));
				
				list.add(articleVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs, ps, conn);
		}
		
		return list;
	}

	@Override
	public long getArticleCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		long result = -1;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) AS cnt FROM t_article ");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getLong("cnt");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs, ps, conn);
		}		
		return result;
	}
	
	@Override
	public long getKeywordArticleCount(String keyword) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		long result = -1;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) AS cnt FROM t_article ");
		sql.append(" WHERE CONTENT like '%'|| ? ||'%' or HASHTAG like '%#' || ? || '%' ");
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, "keyword");
			ps.setString(2, "keyword");
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getLong("cnt");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(rs, ps, conn);
		}		
		return result;
	}

	

}
