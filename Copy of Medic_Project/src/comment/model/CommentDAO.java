package comment.model;

import java.util.List;

public interface CommentDAO {

	boolean saveComment(CommentVO commentVO);

	List<CommentVO> CommentViewer(long article_no);

}
