package article.model;

import java.util.List;

public interface ArticleDAO {

	public boolean insertArticle(ArticleVO articleVO);

	public ArticleVO getArticle(long no);

	public long getArticleCount();

	List<ArticleVO> getArticleList(PageNation pageNation);
	
	List<ArticleVO> getArticleKeywordList(PageNation pageNation);
	
	List<ArticleVO> getUserArticleList(String user_id);

	long getKeywordArticleCount(String keyword);

}
