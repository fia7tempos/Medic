package article.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageNation implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(PageNation.class);
	
	private int pageSize = 18;		
	private int blockSize = 5;		
	
	private String keyword;
	private long pg;				
	private long startNum;			
	private long endNum;			
	private long articleCount;		
		
	private long pageCount = 0;		
	private long startPage = 0;		
	private long endPage = 0;
	
	private StringBuffer display = new StringBuffer();
	
	public PageNation(long pg){
		this.pg = pg;
		startNum = (pg - 1) * pageSize + 1;
		endNum = pg * pageSize;
		
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		articleCount = articleDAO.getArticleCount();
		pageCount = (articleCount + (pageSize - 1)) / pageSize;
		//pageCount = articleCount % pageSize == 0 ? articleCount / pageCount : 
		//										  	 articleCount / pageCount + 1;
		//pageCount = (long)Math.ceil(((double)articleCount / pageSize));
		
		startPage = (pg - 1) / blockSize * blockSize + 1;
		endPage = (startPage - 1) + blockSize;
		if(endPage > pageCount) {
			endPage = pageCount;
		}

		if(startPage != 1) {
			display.append("[<a href='List?pg=" + (startPage - 1) + "'>[previous]</a>]");			
		} else{
			display.append("[[previous]]");			
		}
		
		for(long p=startPage; p<=endPage; p++){
			if(pg == p) {
				display.append(" " + p + " ");				
			} else {
				display.append("<a href='List?pg=" + p + "'> " + p + " </a>");
			}
		}
		
		if(endPage != pageCount ) {
			display.append("[<a href='List?pg=" + (endPage + 1) + "'>[next]</a>]");
		}else{
			display.append("[[next]]");			
		}
	}
	public PageNation(long pg, String keyword){
		this.pg = pg;
		this.keyword = keyword;
		startNum = (pg - 1) * pageSize + 1;
		endNum = pg * pageSize;
		
		ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
		articleCount = articleDAO.getKeywordArticleCount(keyword);
		pageCount = (articleCount + (pageSize - 1)) / pageSize;
		//pageCount = articleCount % pageSize == 0 ? articleCount / pageCount : 
		//										  	 articleCount / pageCount + 1;
		//pageCount = (long)Math.ceil(((double)articleCount / pageSize));
		
		startPage = (pg - 1) / blockSize * blockSize + 1;
		endPage = (startPage - 1) + blockSize;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		if(startPage != 1) {
			display.append("[<a href='List?pg=" + (startPage - 1) + "'>[previous]</a>]");			
		} else{
			display.append("[[previous]]");			
		}
		
		for(long p=startPage; p<=endPage; p++){
			if(pg == p) {
				display.append(" " + p + " ");				
			} else {
				display.append("<a href='List?pg=" + p + "'> " + p + " </a>");
			}
		}
		
		if(endPage != pageCount ) {
			display.append("[<a href='List?pg=" + (endPage + 1) + "'>[next]</a>]");
		}else{
			display.append("[[next]]");			
		}
	}
	
	public String getDisplay() {		
		return display.toString();
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public long getPg() {
		return pg;
	}

	public void setPg(long pg) {
		this.pg = pg;
	}

	public long getStartNum() {
		return startNum;
	}

	public void setStartNum(long startNum) {
		this.startNum = startNum;
	}

	public long getEndNum() {
		return endNum;
	}

	public void setEndNum(long endNum) {
		this.endNum = endNum;
	}

	public long getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(long articleCount) {
		this.articleCount = articleCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getStartPage() {
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}

	public long getEndPage() {
		return endPage;
	}

	public void setEndPage(long endPage) {
		this.endPage = endPage;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public String toString() {
		return "PageNation [pageSize=" + pageSize + ", blockSize=" + blockSize + ", pg=" + pg + ", startNum=" + startNum
				+ ", endNum=" + endNum + ", articleCount=" + articleCount + ", pageCount=" + pageCount + ", startPage="
				+ startPage + ", endPage=" + endPage + "]";
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
		
}
