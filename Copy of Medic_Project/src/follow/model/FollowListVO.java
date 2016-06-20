package follow.model;

public class FollowListVO {



		private long startnum;
		private long endnum;
		private long pg;
		private long startPage;
		private long endPage;
		private long pageCount;
		private long pageSize = 5;
		private long followCount;
		private byte blockSize = 5; //page 한 번에 출력할 수
		
		public long getStartnum() {
			return startnum;
		}
		public void setStartnum(long startnum) {
			this.startnum = startnum;
		}
		public long getEndnum() {
			return endnum;
		}
		public void setEndnum(long endnum) {
			this.endnum = endnum;
		}
		public long getPg() {
			return pg;
		}
		public void setPg(long pg) {
			this.pg = pg;
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
		public long getPageCount() {
			return pageCount;
		}
		public void setPageCount(long pageCount) {
			this.pageCount = pageCount;
		}
		public long getPageSize() {
			return pageSize;
		}
		public void setPageSize(long pageSize) {
			this.pageSize = pageSize;
		}
		public long getFollowCount() {
			return followCount;
		}
		public void setFollowCount(long followCount) {
			this.followCount = followCount;
		}
		public byte getBlockSize() {
			return blockSize;
		}
		public void setBlockSize(byte blockSize) {
			this.blockSize = blockSize;
		}
		
		
		

	
}
